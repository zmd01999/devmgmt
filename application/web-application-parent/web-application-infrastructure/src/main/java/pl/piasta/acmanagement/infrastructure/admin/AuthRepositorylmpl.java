package pl.piasta.acmanagement.infrastructure.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;
import pl.piasta.acmanagement.infrastructure.dao.SysRoleDao;
import pl.piasta.acmanagement.infrastructure.dao.SysUserDao;
import pl.piasta.acmanagement.infrastructure.dao.SysUserRoleDao;
import pl.piasta.acmanagement.infrastructure.mapper.AuthEntityMapper;
import pl.piasta.acmanagement.infrastructure.model.SysUserEntity;

@Repository
@RequiredArgsConstructor
public class AuthRepositorylmpl implements AuthRepository {

    private final SysRoleDao sysRoleDao;
    private final SysUserDao sysUserDao;
    private final SysUserRoleDao sysUserRoleDao;
    private final AuthEntityMapper mapper;

    @Override
    public UserDetail findByUsername(String name) {
        SysUserEntity sysUserEntity = sysUserDao.findByUserName(name);
        if (sysUserEntity == null) {
            return null;
        } else {
            Long roleId = sysUserRoleDao.findByuserId(sysUserEntity.getUserName()).getRoleId();
            Role role = mapper.mapToRole(sysRoleDao.findById(roleId)).orElse(null);
            return mapper.mapToUserDetail(sysUserEntity, role);
        }

    }

    @Override
    public void insert(UserDetail userDetail) {
        sysUserDao.insertUser(userDetail.getUsername(), userDetail.getPassword(), "木风");
    }

    @Override
    public void insertRole(String userId, long roleId) {
         sysUserRoleDao.insertUserRole(userId, roleId);
    }

    @Override
    public Role findRoleById(long roleId) {
            return mapper.mapToRole(sysRoleDao.findById(roleId)).orElse(null);
    }

    @Override
    public Role findRoleByUserId(long userId) {
        Long roleId = sysUserRoleDao.findById(userId).get().getRoleId();
        return mapper.mapToRole(sysRoleDao.findById(roleId)).orElse(null);
    }
}
