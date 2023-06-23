package me.acomma.admin.web.advice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import me.acomma.admin.common.Result;
import me.acomma.admin.common.enums.SystemErrorCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 4)
public class ValidationExceptionHandler {
    @ExceptionHandler
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ObjectError error = exception.getAllErrors().get(0);
        String message = error.getDefaultMessage();
        return Result.<Void>builder().code(SystemErrorCode.INVALID_PARAMETER.code()).message(message).build();
    }

    @ExceptionHandler
    public Result<Void> handleConstraintViolationException(ConstraintViolationException exception) {
        ConstraintViolation<?> violation = exception.getConstraintViolations().iterator().next();
        String message = violation.getMessage();
        return Result.<Void>builder().code(SystemErrorCode.INVALID_PARAMETER.code()).message(message).build();
    }
}
