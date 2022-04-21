package pl.piasta.acmanagement.infrastructure.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ENERGY_CONSUM")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnergyConsumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "SCENE", nullable = false, length = 30)
    private String scene;

    @Column(name = "SEAM", nullable = true, length = 30)
    private String seam;

    @Column(name = "PRODUCT", nullable = false, length = 30)
    private String product;

    @Column(name = "POWER_CONSUM", nullable = false, length = 30)
    private int powerConsum;

    @Column(name = "DATE", nullable = false, length = 50)
    private Date date;


}
