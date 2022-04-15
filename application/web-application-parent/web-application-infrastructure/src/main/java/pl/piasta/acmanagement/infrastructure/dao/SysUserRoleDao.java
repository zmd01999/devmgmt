package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.infrastructure.model.SysUserRoleEntity;

public interface SysUserRoleDao extends JpaRepository<SysUserRoleEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "insert into sys_user_role (userid, roleid) VALUES (?1, ?2)", nativeQuery = true)
    void insertUserRole(String userId, Long roleId);

    SysUserRoleEntity findByuserId(String userId);

}
