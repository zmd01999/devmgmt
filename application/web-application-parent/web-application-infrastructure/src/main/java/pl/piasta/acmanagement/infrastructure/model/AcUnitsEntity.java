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

@Entity
@Table(name = "AC_UNITS")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AcUnitsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "MANUFACTURER", nullable = false, length = 100)
    private String manufacturer;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 100)
    private String productName;

    @Column(name = "VOLTAGE", nullable = false)
    private Integer voltage;

    @Column(name = "CURRENT", nullable = false)
    private Integer current;
}
