package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.piasta.acmanagement.infrastructure.model.EnergyConsumEntity;

public interface EnergyConsumDao extends JpaRepository<EnergyConsumEntity, Long>, JpaSpecificationExecutor<EnergyConsumEntity> {

}
