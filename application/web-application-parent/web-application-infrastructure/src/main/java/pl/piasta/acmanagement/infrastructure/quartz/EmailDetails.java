package pl.piasta.acmanagement.infrastructure.quartz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class EmailDetails {

    private final String email;
    private final String subject;
    private final String body;
}
