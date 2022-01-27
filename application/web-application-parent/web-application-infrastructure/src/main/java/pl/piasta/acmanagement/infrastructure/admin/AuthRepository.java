package pl.piasta.acmanagement.infrastructure.admin;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;

/**
 * @author JoeTao
 * createAt: 2018/9/17
 */
@Repository
public interface AuthRepository {
    /**
     * 根据用户名查找用户
     * @param name
     * @return
     */
    UserDetail findByUsername(@Param("name") String name);

    /**
     * 创建新用户
     * @param userDetail
     */
    void insert(UserDetail userDetail);

    /**
     * 创建用户角色
     * @param userId
     * @param roleId
     * @return
     */
    int insertRole(@Param("userId") long userId, @Param("roleId") long roleId);

    /**
     * 根据角色id查找角色
     * @param roleId
     * @return
     */
    Role findRoleById(@Param("roleId") long roleId);

    /**
     * 根据用户id查找该用户角色
     * @param userId
     * @return
     */
    Role findRoleByUserId(@Param("userId") long userId);
}
