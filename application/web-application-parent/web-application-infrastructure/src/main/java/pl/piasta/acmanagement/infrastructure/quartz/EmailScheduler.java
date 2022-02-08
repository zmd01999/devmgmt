package pl.piasta.acmanagement.infrastructure.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailScheduler {

    private final Scheduler scheduler;

    public String add(EmailDetails emailDetails) {
        String jobKey = UUID.randomUUID().toString();
        JobDetail jobDetail = buildJobDetail(emailDetails, jobKey);
        try {
            scheduler.addJob(jobDetail, true);
        } catch (SchedulerException ex) {
            throw new RuntimeException(ex);
        }
        log.info("Added new email job for {}", emailDetails.getEmail());
        return jobKey;
    }

    public void schedule(String jobKey, LocalDateTime date) {
        try {
            if (scheduler.checkExists(TriggerKey.triggerKey(jobKey, "triggers"))) {
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey, "triggers"));
            }
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(jobKey, "jobs"));
            if (!date.isBefore(LocalDateTime.now())) {
                Trigger trigger = buildJobTrigger(jobDetail, date.toInstant(ZoneOffset.UTC));
                scheduler.scheduleJob(trigger);
            } else {
                scheduler.triggerJob(JobKey.jobKey(jobKey));
            }
        } catch (SchedulerException ex) {
            log.error("Failed to schedule email job", ex);
            return;
        }
        log.info("Scheduled new email job {} at {}", jobKey, date);
    }

    public void unschedule(String jobKey) {
        try {
            if (scheduler.checkExists(TriggerKey.triggerKey(jobKey, "triggers"))) {
                scheduler.unscheduleJob(TriggerKey.triggerKey(jobKey, "triggers"));
            }
        } catch (SchedulerException ex) {
            log.error("Failed to unschedule email job", ex);
            return;
        }
        log.info("Unscheduled email job {}", jobKey);
    }

    public void cancel(String jobKey) {
        try {
            scheduler.deleteJob(JobKey.jobKey(jobKey, "jobs"));
        } catch (SchedulerException ex) {
            log.error("Failed to cancel email job", ex);
            return;
        }
        log.info("Unscheduled email job {}", jobKey);
    }

    private JobDetail buildJobDetail(EmailDetails emailDetails, String jobKey) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("email", emailDetails.getEmail());
        jobDataMap.put("subject", emailDetails.getSubject());
        jobDataMap.put("body", emailDetails.getBody());
        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(jobKey, "jobs")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, Instant startDate) {
        return TriggerBuilder.newTrigger()
                .withIdentity(jobDetail.getKey().getName(), "triggers")
                .startAt(Date.from(startDate))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .forJob(jobDetail.getKey().getName(), "jobs")
                .build();
    }
}
