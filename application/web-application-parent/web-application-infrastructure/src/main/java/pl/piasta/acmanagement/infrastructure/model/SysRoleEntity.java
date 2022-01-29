package pl.piasta.acmanagement.infrastructure.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SYS_ROLE")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SysRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "role_namezh", nullable = false, length = 20)
    private String roleNameZh;

    @Column(name = "role_name",  nullable = false, length = 20)
    private String roleName;

    @Column(name = "gmt_create", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime gmtCreate;

    @Column(name = "gmt_modified", insertable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime gmtModified;



}
