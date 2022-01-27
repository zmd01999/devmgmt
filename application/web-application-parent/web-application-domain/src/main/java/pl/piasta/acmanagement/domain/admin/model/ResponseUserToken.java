package pl.piasta.acmanagement.domain.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zmd
 * createAt: 2022/1/27
 */
@Data
@AllArgsConstructor
public class ResponseUserToken {
    private String token;
    private UserDetail userDetail;
}
