package pl.piasta.acmanagement.api.misc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;
}
