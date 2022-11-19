package me.acomma.admin.web.advice;

import lombok.extern.slf4j.Slf4j;
import me.acomma.admin.common.Result;
import me.acomma.admin.common.enums.ErrorCode;
import me.acomma.admin.common.enums.SystemErrorCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1)
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result<Void> handleException(Exception exception) {
        log.error("发生未知异常", exception);
        ErrorCode errorCode = SystemErrorCode.SYSTEM_ERROR;
        return Result.<Void>builder().code(errorCode.code()).message(errorCode.message()).build();
    }
}
