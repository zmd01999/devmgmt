package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.infrastructure.model.SysRoleEntity;

import java.util.Optional;

public interface SysRoleDao extends JpaRepository<SysRoleEntity, Long> {
    <T> Optional<T> findById(Long id, Class<T> type);

}
