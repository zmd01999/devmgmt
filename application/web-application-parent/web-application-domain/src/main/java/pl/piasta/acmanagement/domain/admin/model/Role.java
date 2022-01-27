package pl.piasta.acmanagement.domain.admin.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author : zmd
 * createAt: 2022/1/27
 */
@Data
@Builder
public class Role {
    private Long id;
    private String name;
    private String nameZh;
}
