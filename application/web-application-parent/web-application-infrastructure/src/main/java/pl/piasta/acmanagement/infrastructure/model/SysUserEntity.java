package pl.piasta.acmanagement.infrastructure.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SYS_USER")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SysUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "USERNAME", nullable = false, length = 20)
    private String userName;

    @Column(name = "NIKENAME", length = 20)
    private String nikeName;

    @Column(name = "PASSWORD", nullable = false, length = 64)
    private String passWord;
}
