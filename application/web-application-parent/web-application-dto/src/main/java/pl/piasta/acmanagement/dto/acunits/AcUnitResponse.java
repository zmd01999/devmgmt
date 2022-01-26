package pl.piasta.acmanagement.dto.acunits;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class AcUnitResponse {

    private final Long id;
    private final String manufacturer;
    private final String productName;
    private final Integer voltage;
    private final Integer current;
}
