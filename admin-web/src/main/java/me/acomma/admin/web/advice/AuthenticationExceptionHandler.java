package me.acomma.admin.web.advice;

import lombok.extern.slf4j.Slf4j;
import me.acomma.admin.common.Result;
import me.acomma.admin.common.enums.SystemErrorCode;
import me.acomma.admin.web.i18n.MessageUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 6)
@Slf4j
public class AuthenticationExceptionHandler {
    @ExceptionHandler
    public Result<Void> handleAuthenticationException(AuthenticationException exception) {
        log.error("发生用户认证异常", exception);
        SystemErrorCode errorCode = SystemErrorCode.AUTHENTICATION_FAILED;
        Integer code = errorCode.code();
        String message = StringUtils.hasText(exception.getMessage()) ? exception.getMessage() : MessageUtils.getMessage(code.toString(), null, errorCode.message());
        return Result.<Void>builder().code(errorCode.code()).message(message).build();
    }
}
