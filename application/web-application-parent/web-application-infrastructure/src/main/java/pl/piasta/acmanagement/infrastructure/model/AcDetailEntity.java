package pl.piasta.acmanagement.infrastructure.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AC_DETAIL")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AcDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "SYSTEM_ID", nullable = false)
    private Long systemId;

    @Column(name = "power", nullable = false)
    @ColumnDefault("false")
    private boolean power;
    //上下风摆
    @Column(name = "fan_v", nullable = false)
    @ColumnDefault("false")
    private boolean fanV;

    //水平风摆
    @Column(name = "fan_h", nullable = false)
    @ColumnDefault("false")
    private boolean fanH;

    @Column(name = "fan_speed", nullable = false)
    @ColumnDefault("0")
    private int fanSpeed;

    @Column(name = "temp", nullable = false)
    @ColumnDefault("25")
    private int temp;

    @Column(name = "work_mode", nullable = false)
    @ColumnDefault("8")
    private int workMode;

    @Column(name = "preset_mode", nullable = false)
    @ColumnDefault("0")
    private int presetMode;
}
