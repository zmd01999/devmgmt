package pl.piasta.acmanagement.api.admin;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pl.piasta.acmanagement.api.common.ResultCode;
import pl.piasta.acmanagement.api.common.VResponse;
import pl.piasta.acmanagement.domain.admin.model.ResponseUserToken;
import pl.piasta.acmanagement.domain.admin.model.Role;
import pl.piasta.acmanagement.domain.admin.model.User;
import pl.piasta.acmanagement.domain.admin.model.UserDetail;

import pl.piasta.acmanagement.api.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author zmd
 * createAt: 2022/1/27
 */

@RestController
@RequestMapping("/api/v1/admin")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
//    @ApiOperation(value = "登陆", notes = "登陆成功返回token,登陆之前请先注册账号")
    public VResponse<ResponseUserToken> login(
            @Valid @RequestBody User user){
        final ResponseUserToken response = authService.login(user.getName(), user.getPassword());
        return VResponse.success(response);
    }

    @GetMapping(value = "/logout")
    public VResponse<UserDetail> logout(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return VResponse.error(ResultCode.UNAUTHORIZED.getCode(), "登出成功");
        }
        authService.logout(token);
        return VResponse.success();
    }

    @GetMapping(value = "/user")
    public VResponse<UserDetail> getUser(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return VResponse.error(ResultCode.UNAUTHORIZED.getCode(), "获取用户信息成功");
        }
        UserDetail userDetail = authService.getUserByToken(token);
        return VResponse.success(userDetail);
    }

    @PostMapping(value = "/register")
    public VResponse<UserDetail> sign(@RequestBody User user) {
        if (StringUtils.isAnyBlank(user.getName(), user.getPassword())) {
            return VResponse.error(ResultCode.BAD_REQUEST.getCode(), "BAD_REQUEST");
        }
        UserDetail userDetail = new UserDetail(user.getName(), user.getPassword(), Role.builder().id(1L).build());
        return VResponse.success(authService.register(userDetail));
    }
//    @GetMapping(value = "refresh")
//    @ApiOperation(value = "刷新token")
//    public ResultJson refreshAndGetAuthenticationToken(
//            HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        ResponseUserToken response = authService.refresh(token);
//        if(response == null) {
//            return ResultJson.failure(ResultCode.BAD_REQUEST, "token无效");
//        } else {
//            return ResultJson.ok(response);
//        }
//    }
}
