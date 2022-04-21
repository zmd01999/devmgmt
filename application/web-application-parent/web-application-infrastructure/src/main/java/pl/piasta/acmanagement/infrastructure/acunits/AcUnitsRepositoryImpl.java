package pl.piasta.acmanagement.infrastructure.acunits;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.domain.acunits.model.EnergyConsum;
import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.infrastructure.dao.AcUnitsDao;
import pl.piasta.acmanagement.infrastructure.dao.EnergyConsumDao;
import pl.piasta.acmanagement.infrastructure.mapper.AcUnitsEntityMapper;
import pl.piasta.acmanagement.infrastructure.model.AcUnitsEntity;
import pl.piasta.acmanagement.infrastructure.model.EnergyConsumEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AcUnitsRepositoryImpl implements AcUnitsRepository {

    private final AcUnitsDao acUnitsDao;
    private final EnergyConsumDao energyConsumDao;
    private final AcUnitsEntityMapper mapper;

    @Override
    @Transactional
    public Long add(AcUnit unit) {
        AcUnitsEntity entity = new AcUnitsEntity();
        updateEntity(entity, unit);
        return acUnitsDao.save(entity).getId();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(Long id) {
        return acUnitsDao.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AcUnit> get(Long id) {
        Optional<AcUnitsEntity> entity = acUnitsDao.findById(id);
        return mapper.mapToAcUnitOptional(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcUnit> getAll() {
        List<AcUnitsEntity> entityList = acUnitsDao.findAll();
        return mapper.mapToAcUnitList(entityList);
    }

    @Override
    public List<EnergyConsum> getConsum(Date start, Date end) {
        Specification<EnergyConsumEntity> specification = (Specification<EnergyConsumEntity>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            //条件1：EnergyConsumEntity 运行日期（date）大于等于 start 的数据，root.get 中的 date 必须对应 EnergyConsumEntity 中的属性
            predicateList.add(cb.greaterThanOrEqualTo(root.get("date").as(Date.class), start));

            //条件2：EnergyConsumEntity 运行日期（date）小于等于 end
            predicateList.add(cb.lessThanOrEqualTo(root.get("date").as(Date.class), end));

            Predicate[] pre = new Predicate[predicateList.size()];
            pre = predicateList.toArray(pre);
            return query.where(pre).getRestriction();

        };
        return mapper.mapToEnergyCList(energyConsumDao.findAll(specification));//没有数据时，返回空列表

    }



    private void updateEntity(AcUnitsEntity entity, AcUnit unit) {
        if (unit.getId() != null) {
            entity.setId(unit.getId());
        }
        entity.setManufacturer(unit.getManufacturer());
        entity.setProductName(unit.getProductName());
        entity.setCurrent(unit.getCurrent());
        entity.setVoltage(unit.getVoltage());
    }
}
