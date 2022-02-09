package pl.piasta.acmanagement.infrastructure.quartz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import pl.piasta.acmanagement.domain.acsystems.model.AcDetail;
import pl.piasta.acmanagement.infrastructure.acsystems.AcDetailRepository;
import pl.piasta.acmanagement.infrastructure.redis.RedisUtil;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class SyncDataJob extends QuartzJobBean {


    private final AcDetailRepository acDetailRepository;

    private final RedisUtil redisUtil;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Executing job id: {}", jobExecutionContext.getJobDetail().getKey());
        String keyPattern = "Detail_*";
        Set<String> keys = redisUtil.getAllObject(keyPattern);
        for(String key : keys) {
            AcDetail acDetail = (AcDetail)redisUtil.getObject(key);
            acDetailRepository.add(acDetail);
        }
    }
}
