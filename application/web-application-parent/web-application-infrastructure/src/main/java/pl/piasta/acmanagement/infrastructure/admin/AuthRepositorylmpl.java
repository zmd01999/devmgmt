package pl.piasta.acmanagement.infrastructure.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;

@Repository
@RequiredArgsConstructor
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
