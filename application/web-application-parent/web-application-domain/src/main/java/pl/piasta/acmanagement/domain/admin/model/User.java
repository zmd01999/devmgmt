package pl.piasta.acmanagement.domain.admin.model;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Size;

/**
 * @author : zmd
 * createAt: 2022/1/27
 */
@Builder
@Data
public class User {
    @Size(min=6, max=20)
    private String name;
    @Size(min=8, max=20)
    private String password;
}
