package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasta.acmanagement.infrastructure.model.CustomersEntity;

public interface CustomersDao extends JpaRepository<CustomersEntity, Long> {

    boolean existsByDocumentId(String documentId);
}
