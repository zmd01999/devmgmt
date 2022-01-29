package pl.piasta.acmanagement.domain.admin.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

/**
 * @author : zmd
 * createAt: 2022/1/27
 */
@Data
@Builder
@Value
public class Role {
    private Long id;
    private String roleName;
    private String roleNameZh;
}
