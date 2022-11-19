package me.acomma.admin.web.advice;

import lombok.extern.slf4j.Slf4j;
import me.acomma.admin.common.Result;
import me.acomma.admin.common.enums.ErrorCode;
import me.acomma.admin.common.enums.SystemErrorCode;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 2)
@Slf4j
public class DatabaseExceptionHandler {
    @ExceptionHandler
    public Result<Void> handleSQLException(SQLException exception) {
        log.error("访问数据库异常", exception);
        ErrorCode errorCode = SystemErrorCode.ACCESS_DATABASE_FAILED;
        return Result.<Void>builder().code(errorCode.code()).message(errorCode.message()).build();
    }
}
