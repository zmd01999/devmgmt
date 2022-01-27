package pl.piasta.acmanagement.infrastructure.service.lmpl;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;
import pl.piasta.acmanagement.infrastructure.admin.AuthRepository;

/**
 * 登陆身份认证
 * @author: zmd
 * createAt: 2022/1/27
 */
@Component(value="CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final AuthRepository authRepository;

    public CustomUserDetailsServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetail userDetail = authRepository.findByUsername(name);
        if (userDetail == null) {
            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
        }
        Role role = authRepository.findRoleByUserId(userDetail.getId());
        userDetail.setRole(role);
        return userDetail;
    }
}
