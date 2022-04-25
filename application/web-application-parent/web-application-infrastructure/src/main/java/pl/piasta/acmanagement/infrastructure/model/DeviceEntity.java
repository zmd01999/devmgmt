package pl.piasta.acmanagement.infrastructure.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "DEVICE_ALL")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "TYPE", nullable = false)
    private String type;



    @Column(name = "NIKE_NAME", nullable = false)
    private String nikeName;

    @Column(name = "STATUS", nullable = false)
    private String status;


}
