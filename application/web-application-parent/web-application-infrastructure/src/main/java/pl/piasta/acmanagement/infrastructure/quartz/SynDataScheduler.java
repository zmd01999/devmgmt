package pl.piasta.acmanagement.infrastructure.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;


@Component
@RequiredArgsConstructor
@Slf4j
public class SynDataScheduler {
    private final Scheduler scheduler;
    public void schedule()  {
        try {
            String jobKey = UUID.randomUUID().toString();
            JobDetail jobDetail = JobBuilder.newJob(SyncDataJob.class)
                    .withIdentity(jobKey, "jobs")
                    .storeDurably()
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("synData", "syn")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 59 23 * * ?"))
                    .forJob("synData", "syn")
                    .build();
            scheduler.addJob(jobDetail, true);
            scheduler.scheduleJob(trigger);
            log.info("Do Syn job : 23:59");
        } catch (SchedulerException ex) {
            log.error("Failed to schedule Syn job", ex);
        }

    }
}
