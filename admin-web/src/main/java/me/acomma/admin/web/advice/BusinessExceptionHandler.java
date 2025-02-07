package me.acomma.admin.web.advice;

import lombok.extern.slf4j.Slf4j;
import me.acomma.admin.common.Result;
import me.acomma.admin.common.enums.BusinessErrorCode;
import me.acomma.admin.common.exception.BusinessException;
import me.acomma.admin.web.i18n.MessageUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 3)
@Slf4j
public class BusinessExceptionHandler {
    @ExceptionHandler
    public Result<Void> handleBusinessException(BusinessException exception) {
        log.error("发生业务异常", exception);
        BusinessErrorCode businessErrorCode = exception.getBusinessErrorCode();
        Integer code = businessErrorCode.code();
        String message = MessageUtils.getMessage(code.toString(), exception.getArguments(), businessErrorCode.message());
        return Result.<Void>builder().code(code).message(message).build();
    }
}
