package pl.piasta.acmanagement.domain.acunits.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class EnergyConsum {

    private Long id;

    private String scene;

    private String seam;

    private String product;

    private double powerConsum;

    private double smartConsum;

    private double tradConsum;

    private Date date;

}
