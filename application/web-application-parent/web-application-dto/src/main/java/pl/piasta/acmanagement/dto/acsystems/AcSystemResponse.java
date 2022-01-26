package pl.piasta.acmanagement.dto.acsystems;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public final class AcSystemResponse {

    private final Long id;
    private final Long unitId;
    private final LocalDateTime nextMaintainance;
    private final boolean notified;
    private final Long customerId;
}
