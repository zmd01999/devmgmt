package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.piasta.acmanagement.infrastructure.model.DeviceEntity;

import java.util.List;

public interface DevicesDao extends JpaRepository<DeviceEntity, Long> , JpaSpecificationExecutor<DeviceEntity> {
    List<DeviceEntity> findByType(String type);
}
