package pl.piasta.acmanagement.infrastructure.admin;

import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;

public class AuthRepositorylmpl implements AuthRepository {
    @Override
    public UserDetail findByUsername(String name) {
        return null;
    }

    @Override
    public void insert(UserDetail userDetail) {

    }

    @Override
    public int insertRole(long userId, long roleId) {
        return 0;
    }

    @Override
    public Role findRoleById(long roleId) {
        return null;
    }

    @Override
    public Role findRoleByUserId(long userId) {
        return null;
    }
}
