package pl.piasta.acmanagement.api.service;


import pl.piasta.acmanagement.domain.admin.model.ResponseUserToken;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;

/**
 * @author: zmd
 * createAt: 2022/1/27
 */
public interface AuthService {
    /**
     * 注册用户
     * @param userDetail
     * @return
     */
    UserDetail register(UserDetail userDetail);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    ResponseUserToken login(String username, String password);

    /**
     * 登出
     * @param token
     */
    void logout(String token);

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
    ResponseUserToken refresh(String oldToken);

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    UserDetail getUserByToken(String token);
}
