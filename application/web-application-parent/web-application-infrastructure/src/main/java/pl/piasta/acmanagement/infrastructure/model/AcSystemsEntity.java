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
import java.time.LocalDateTime;

@Entity
@Table(name = "AC_SYSTEMS")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AcSystemsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="unit_id")
    private AcUnitsEntity unit;

    @Column(name = "NEXT_MAINTAINANCE", nullable = false)
    private LocalDateTime nextMaintainance;

    @Column(name = "NOTIFICATIONS", nullable = false)
    private boolean notifications;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    CustomersEntity customer;

    @Column(name = "JOB_KEY", nullable = false)
    private String jobKey;
}
