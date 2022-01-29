package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.piasta.acmanagement.infrastructure.model.SysRoleEntity;
import pl.piasta.acmanagement.infrastructure.model.SysUserRoleEntity;

public interface SysUserRoleDao extends JpaRepository<SysUserRoleEntity, Long> {

    @Query(value = "insert into sys_user_role (user_id, role_id) VALUES (?1, ?2)", nativeQuery = true)
    void insertUserRole(Long userId, Long roleId);

}
