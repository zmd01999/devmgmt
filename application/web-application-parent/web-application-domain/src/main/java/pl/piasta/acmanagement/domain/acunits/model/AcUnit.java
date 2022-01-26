package pl.piasta.acmanagement.domain.acunits.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AcUnit {

    private Long id;
    private String manufacturer;
    private String productName;
    private Integer voltage;
    private Integer current;
}
