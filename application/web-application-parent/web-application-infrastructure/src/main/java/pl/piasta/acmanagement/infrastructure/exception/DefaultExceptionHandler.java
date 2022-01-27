package pl.piasta.acmanagement.infrastructure.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.piasta.acmanagement.infrastructure.common.ResultCode;
import pl.piasta.acmanagement.infrastructure.common.VResponse;

/**
 * @author zmd
 * 异常处理类
 * controller层异常无法捕获处理，需要自己处理
 * Created at 2022/1/27.
 */
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    /**
     * 处理所有自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public VResponse handleCustomException(CustomException e){
        log.error(e.getVResponse().getMsg());
        return e.getVResponse();
    }
    /**
     * 处理参数校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public VResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e.getBindingResult().getFieldError().getField() + e.getBindingResult().getFieldError().getDefaultMessage());
        return VResponse.error(ResultCode.BAD_REQUEST.getCode(), e.getBindingResult().getFieldError().getDefaultMessage());
    }
}
