package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasta.acmanagement.infrastructure.model.AcUnitsEntity;

public interface AcUnitsDao extends JpaRepository<AcUnitsEntity, Long> {
}
