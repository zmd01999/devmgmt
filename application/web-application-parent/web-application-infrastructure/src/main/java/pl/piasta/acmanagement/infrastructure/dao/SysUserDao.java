package pl.piasta.acmanagement.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.transaction.annotation.Transactional;
import pl.piasta.acmanagement.infrastructure.model.SysUserEntity;


public interface SysUserDao extends JpaRepository<SysUserEntity, Long> {
    SysUserEntity findByUserName(String userName);

    @Transactional
    @Modifying
    @Query(value = "insert into sys_user (username, password, nikename) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertUser(String userName, String password, String nikename);
}
