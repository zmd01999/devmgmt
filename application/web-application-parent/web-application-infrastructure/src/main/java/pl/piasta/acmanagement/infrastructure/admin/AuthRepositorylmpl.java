package pl.piasta.acmanagement.infrastructure.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;
import pl.piasta.acmanagement.infrastructure.dao.SysRoleDao;
import pl.piasta.acmanagement.infrastructure.dao.SysUserDao;
import pl.piasta.acmanagement.infrastructure.dao.SysUserRoleDao;

@Repository
@RequiredArgsConstructor
public class AuthRepositorylmpl implements AuthRepository {

    private final SysRoleDao sysRoleDao;
    private final SysUserDao sysUserDao;
    private final SysUserRoleDao sysUserRoleDao;

    @Override
    public UserDetail findByUsername(String name) {

        return sysUserDao.findByUserName(name, UserDetail.class).get();
    }

    @Override
    public void insert(UserDetail userDetail) {
        sysUserDao.insertUser(userDetail.getUsername(), userDetail.getPassword());
    }

    @Override
    public void insertRole(long userId, long roleId) {
         sysUserRoleDao.insertUserRole(userId, roleId);
    }

    @Override
    public Role findRoleById(long roleId) {
            return sysRoleDao.findById(roleId, Role.class).get();
    }

    @Override
    public Role findRoleByUserId(long userId) {
        String roleId = sysUserRoleDao.findById(userId).get().getRoleId();
        return sysRoleDao.findById(Long.parseLong(roleId), Role.class).get();
    }
}
