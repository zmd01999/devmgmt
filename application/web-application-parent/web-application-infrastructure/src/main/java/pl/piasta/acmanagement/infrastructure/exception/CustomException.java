package pl.piasta.acmanagement.infrastructure.exception;


import lombok.Getter;
import pl.piasta.acmanagement.infrastructure.common.VResponse;

/**
 * @author zmd
 * Created at 2022/1/27.
 */
@Getter
public class CustomException extends RuntimeException{
    private VResponse vResponse;

    public CustomException(VResponse vResponse) {
        this.vResponse = vResponse;
    }
}
