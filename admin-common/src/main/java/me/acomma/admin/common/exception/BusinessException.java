package me.acomma.admin.common.exception;

import lombok.Getter;
import me.acomma.admin.common.enums.BusinessErrorCode;

import java.io.Serial;

@Getter
public class BusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final BusinessErrorCode businessErrorCode;

    public BusinessException(BusinessErrorCode businessErrorCode) {
        super(businessErrorCode.message());
        this.businessErrorCode = businessErrorCode;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String message) {
        super(message);
        this.businessErrorCode = businessErrorCode;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String message, Throwable cause) {
        super(message, cause);
        this.businessErrorCode = businessErrorCode;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, Throwable cause) {
        super(cause);
        this.businessErrorCode = businessErrorCode;
    }
}
