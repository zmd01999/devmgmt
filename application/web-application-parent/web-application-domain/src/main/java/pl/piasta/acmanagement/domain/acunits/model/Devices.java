package pl.piasta.acmanagement.domain.acunits.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Devices {
    private Long id;

    private String type;

    private String userName;

    private String nikeName;

    private String status;
}
