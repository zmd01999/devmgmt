package pl.piasta.acmanagement.domain.acsystems.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class JobDetails {

    private final String jobKey;
    private final LocalDateTime nextMaintainance;
    private final boolean notified;
}
