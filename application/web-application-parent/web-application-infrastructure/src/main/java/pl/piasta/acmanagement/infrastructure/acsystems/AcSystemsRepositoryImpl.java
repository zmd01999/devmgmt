package pl.piasta.acmanagement.infrastructure.acsystems;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.domain.acsystems.AcSystemsRepository;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystem;
import pl.piasta.acmanagement.domain.acsystems.model.AcSystemFull;
import pl.piasta.acmanagement.domain.acsystems.model.JobDetails;
import pl.piasta.acmanagement.infrastructure.dao.AcSystemsDao;
import pl.piasta.acmanagement.infrastructure.mapper.AcSystemsEntityMapper;
import pl.piasta.acmanagement.infrastructure.model.AcSystemsEntity;
import pl.piasta.acmanagement.infrastructure.model.AcUnitsEntity;
import pl.piasta.acmanagement.infrastructure.model.CustomersEntity;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AcSystemsRepositoryImpl implements AcSystemsRepository {

    private final AcSystemsDao dao;
    private final EntityManager entityManager;
    private final AcSystemsEntityMapper mapper;

    @Override
    @Transactional
    public Long add(AcSystem unit, String jobKey) {
        AcSystemsEntity entity = new AcSystemsEntity();
        updateEntity(entity, unit, jobKey);
        return dao.save(entity).getId();
    }

    @Override
    @Transactional
    public Optional<String> remove(Long id) {
        return dao.findById(id).map(entity -> {
            dao.delete(entity);
            return entity.getJobKey();
        });
    }

    @Override
    @Transactional
    public Optional<JobDetails> updateNextMaintainance(Long id, LocalDateTime date) {
        return dao.findById(id).map(entity -> {
            entity.setNextMaintainance(date);
            return mapper.mapToJobDetails(entity);
        });
    }

    @Override
    @Transactional
    public Optional<JobDetails> updateNotificationsStatus(Long id, boolean enabled) {
        return dao.findById(id).map(entity -> {
            entity.setNotifications(enabled);
            return mapper.mapToJobDetails(entity);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AcSystemFull> get(Long id) {
        Optional<AcSystemsEntity> entity = dao.findById(id);
        return mapper.mapToAcSystemFullOptional(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcSystem> getAll() {
        List<AcSystemsEntity> entityList = dao.findAll();
        return mapper.mapToAcSystemList(entityList);
    }

    void updateEntity(AcSystemsEntity entity, AcSystem system, String jobKey) {
        if (system.getId() != null) {
            entity.setId(system.getId());
        }
        entity.setNextMaintainance(system.getNextMaintainance());
        entity.setNotifications(system.isNotified());
        entity.setCustomer(entityManager.find(CustomersEntity.class, system.getCustomerId()));
        entity.setUnit(entityManager.find(AcUnitsEntity.class, system.getUnitId()));
        entity.setJobKey(jobKey);
    }
}
