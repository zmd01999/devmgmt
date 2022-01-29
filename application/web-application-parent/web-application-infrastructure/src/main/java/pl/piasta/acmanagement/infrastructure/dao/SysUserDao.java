package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;
import pl.piasta.acmanagement.infrastructure.model.SysRoleEntity;
import pl.piasta.acmanagement.infrastructure.model.SysUserEntity;

public interface SysUserDao extends JpaRepository<SysUserEntity, Long> {
    UserDetail findByUserName(String userName);

    @Query(value = "insert into sys_user (name, password) VALUES (?1, ?2)", nativeQuery = true)
    void insertUser(String userName, String password);
}
