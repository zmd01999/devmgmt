package pl.piasta.acmanagement.domain.acsystems.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public final class AcSystem {

    private final Long id;
    private final Long unitId;
    private final LocalDateTime nextMaintainance;
    private final boolean notified;
    private final Long customerId;
}
