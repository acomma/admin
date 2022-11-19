package me.acomma.admin.web.advice;

import lombok.extern.slf4j.Slf4j;
import me.acomma.admin.common.Result;
import me.acomma.admin.common.enums.BusinessErrorCode;
import me.acomma.admin.common.exception.BusinessException;
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
        return Result.<Void>builder().code(businessErrorCode.code()).message(businessErrorCode.message()).build();
    }
}
