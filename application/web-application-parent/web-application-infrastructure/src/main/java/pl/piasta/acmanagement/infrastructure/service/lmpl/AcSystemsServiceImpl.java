package pl.piasta.acmanagement.infrastructure.service.lmpl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.api.service.AcSystemsService;
import pl.piasta.acmanagement.domain.acsystems.model.AcDetail;
import pl.piasta.acmanagement.infrastructure.acsystems.AcDetailRepository;
import pl.piasta.acmanagement.infrastructure.acsystems.AcSystemsRepository;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystem;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystemFull;
import pl.piasta.acmanagement.domain.acsystems.model.JobDetails;
import pl.piasta.acmanagement.infrastructure.quartz.EmailDetails;
import pl.piasta.acmanagement.infrastructure.quartz.EmailScheduler;
import pl.piasta.acmanagement.infrastructure.acunits.AcUnitsRepository;
import pl.piasta.acmanagement.infrastructure.customers.CustomersRepository;
import pl.piasta.acmanagement.domain.customers.model.Customer;
import pl.piasta.acmanagement.domain.misc.ErrorCode;


import pl.piasta.acmanagement.api.misc.MyException;
import pl.piasta.acmanagement.infrastructure.redis.RedisUtil;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcSystemsServiceImpl implements AcSystemsService {

    private final AcSystemsRepository acSystemsRepository;
    private final AcUnitsRepository acUnitsRepository;
    private final CustomersRepository customersRepository;
    private final EmailScheduler emailScheduler;
    private final RedisUtil redisUtil;
    private static final String CACHE_DETAIL = "detail_";

    @Resource
    private AcDetailRepository acDetailRepository;

    @Override
    @Transactional
    public Long addSystem(AcSystem system) {
        if (!acUnitsRepository.exists(system.getUnitId())) {
            throw new MyException(ErrorCode.UNIT_NOT_EXISTS);
        }
        String email = customersRepository.get(system.getCustomerId())
                .map(Customer::getEmail)
                .orElseThrow(() -> new MyException(ErrorCode.CUSTOMER_NOT_EXISTS));
        EmailDetails emailDetails = createEmailDetails(email);
        String jobKey = emailScheduler.add(emailDetails);
        if (system.isNotified()) {
            emailScheduler.schedule(jobKey, system.getNextMaintainance().minusDays(7));
        }
        //初始化空调状态
        Long id =  acSystemsRepository.add(system, jobKey);
        AcDetail acDetail = new AcDetail(id);
        Long detailId = acDetailRepository.add(acDetail);
        String key = CACHE_DETAIL + detailId;
        //缓存初始化
        redisUtil.setObject(key, acDetail);
        return id;
    }

    @Override
    @Transactional
    public void removeSystemById(Long id) {
        acSystemsRepository.remove(id)
                .ifPresentOrElse(emailScheduler::cancel, () -> { throw new MyException(ErrorCode.SYSTEM_NOT_EXISTS); });
    }

    @Override
    @Transactional(readOnly = true)
    public AcSystemFull getSystemById(Long id) {
        return acSystemsRepository.get(id)
                .orElseThrow(() -> new MyException(ErrorCode.SYSTEM_NOT_EXISTS));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcSystem> getAllSystems() {
        return acSystemsRepository.getAll();
    }

    @Override
    @Transactional
    public void setNextMaintainance(Long id, LocalDateTime date) {
        acSystemsRepository.updateNextMaintainance(id, date)
                .ifPresentOrElse(this::scheduleEmail, () -> { throw new MyException(ErrorCode.SYSTEM_NOT_EXISTS); });
    }

    @Override
    @Transactional
    public void setNotifications(Long id, boolean enabled) {
        acSystemsRepository.updateNotificationsStatus(id, enabled)
                .ifPresentOrElse(this::scheduleEmail, () -> { throw new MyException(ErrorCode.SYSTEM_NOT_EXISTS); });
    }

    //************************************************SystemDetail********************************************************//

    @Override
    public AcDetail updateDetail(AcDetail acDetail) {
        String key = CACHE_DETAIL + acDetail.getId();
        redisUtil.setObject(key, acDetail);
        return acDetail;
    }

    @Override
    public AcDetail getDetail(Long id) {
        String key = CACHE_DETAIL + id;
        AcDetail acDetail = (AcDetail) redisUtil.getObject(key);
        if(acDetail == null) {
            //防止缓存穿透情况
            if(redisUtil.exists(key)){
                return null;
            }
            acDetail = acDetailRepository.findByid(id);
            redisUtil.setObject(key, acDetail);
        }
        return acDetail;
    }

    //************************************************Private********************************************************//

    private void scheduleEmail(JobDetails jobDetails) {
        LocalDateTime date = jobDetails.getNextMaintainance();
        if (jobDetails.isNotified() && date.isBefore(LocalDateTime.now())) {
            emailScheduler.schedule(jobDetails.getJobKey(), date.minusDays(7));
        } else {
            emailScheduler.unschedule(jobDetails.getJobKey());
        }
    }

    private EmailDetails createEmailDetails(String email) {
        return new EmailDetails(
                email,
                "ACManagement - maintainance reminder",
                "<h1>Hello!</h1><br>" +
                        "Just a quick reminder - it's week until the scheduled AC system maintainance!<br>" +
                        "Gretings.");
    }
}
