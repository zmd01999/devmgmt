package pl.piasta.acmanagement.api.misc;

import lombok.Getter;
import pl.piasta.acmanagement.domain.misc.ErrorCode;

@Getter
public class MyException extends RuntimeException {

    private final ErrorCode errorCode;

    public MyException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
