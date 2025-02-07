package me.acomma.admin.web.advice;

import lombok.extern.slf4j.Slf4j;
import me.acomma.admin.common.Result;
import me.acomma.admin.common.enums.SystemErrorCode;
import me.acomma.admin.web.i18n.MessageUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 参考 <a href="https://github.com/spring-projects/spring-security/issues/6908">AccessDeniedHandler and AuthenticationEntryPoint does not work Because of the global exception handler</a>
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 5)
@Slf4j
public class AccessDeniedExceptionHandler {
    @ExceptionHandler
    public Result<Void> handleAccessDeniedException(AccessDeniedException exception) {
        log.error("发生用户无权限异常", exception);
        SystemErrorCode errorCode = SystemErrorCode.UNAUTHORIZED;
        Integer code = errorCode.code();
        String message = MessageUtils.getMessage(code.toString(), null, errorCode.message());
        return Result.<Void>builder().code(code).message(message).build();
    }
}
