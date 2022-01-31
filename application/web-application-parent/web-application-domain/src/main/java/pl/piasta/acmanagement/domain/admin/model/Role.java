package pl.piasta.acmanagement.domain.admin.model;

import lombok.*;

/**
 * @author : zmd
 * createAt: 2022/1/27
 */
@Data
@Builder
@RequiredArgsConstructor
@Getter
@Value
public class Role {
    private Long id;
    private String roleName;
    private String roleNameZh;
}
