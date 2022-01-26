package pl.piasta.acmanagement.dto.acsystems;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.piasta.acmanagement.dto.acunits.AcUnitResponse;
import pl.piasta.acmanagement.dto.customers.CustomerResponse;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public final class AcSystemFullResponse {

    private final Long id;
    private final AcUnitResponse unit;
    private final LocalDateTime nextMaintainance;
    private final boolean notified;
    private final CustomerResponse customer;
}
