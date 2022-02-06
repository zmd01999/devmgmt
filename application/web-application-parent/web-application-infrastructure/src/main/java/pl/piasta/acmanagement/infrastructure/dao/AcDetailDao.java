package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasta.acmanagement.infrastructure.model.AcDetailEntity;

public interface AcDetailDao extends JpaRepository<AcDetailEntity, Long> {
}
