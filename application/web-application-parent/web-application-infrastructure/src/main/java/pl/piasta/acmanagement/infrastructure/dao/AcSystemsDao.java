package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasta.acmanagement.infrastructure.model.AcSystemsEntity;

public interface AcSystemsDao extends JpaRepository<AcSystemsEntity, Long> {
}
