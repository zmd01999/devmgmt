package pl.piasta.acmanagement.domain.acsystems.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import pl.piasta.acmanagement.domain.acunits.model.AcUnit;
import pl.piasta.acmanagement.domain.customers.model.Customer;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public final class AcSystemFull {

    private final Long id;
    private final AcUnit unit;
    private final LocalDateTime nextMaintainance;
    private final boolean notified;
    private final Customer customer;
}
