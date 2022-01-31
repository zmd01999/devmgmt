package pl.piasta.acmanagement.infrastructure.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sys_user_role")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SysUserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "USERID", length = 20)
    private Long userId;

    @Column(name = "ROLEID", length = 20)
    private Long roleId;
}
