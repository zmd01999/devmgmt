package pl.piasta.acmanagement.dto.acunits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Getter
public class EnergyAdvResponse {

    private final String scene;

    private final double powerConsum;

    private final double smartConsum;

    private final double tradConsum;

    private final Date date;
}
