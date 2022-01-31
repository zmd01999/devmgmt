package pl.piasta.acmanagement.infrastructure.mapper;

import org.springframework.stereotype.Component;
import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;
import pl.piasta.acmanagement.infrastructure.model.AcUnitsEntity;
import pl.piasta.acmanagement.infrastructure.model.SysRoleEntity;
import pl.piasta.acmanagement.infrastructure.model.SysUserEntity;

import java.util.Optional;

@Component
public class AuthEntityMapper {

    public Optional<Role> mapToRole( Optional<SysRoleEntity> sysRoleEntity){
        return sysRoleEntity.map(this::mapToRoleFull);
    }

    public UserDetail mapToUserDetail(SysUserEntity sysUserEntity, Role role){
        return new UserDetail(
                sysUserEntity.getId(),
                sysUserEntity.getUserName(),
                role,
                sysUserEntity.getPassWord()
        );
    }

    private Role mapToRoleFull(SysRoleEntity sysRoleEntity){
        return new Role(
                sysRoleEntity.getId(),
                sysRoleEntity.getRoleName(),
                sysRoleEntity.getRoleNameZh()
        );
    }
}
