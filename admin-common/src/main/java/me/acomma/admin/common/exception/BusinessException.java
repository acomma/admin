package me.acomma.admin.common.exception;

import lombok.Getter;
import me.acomma.admin.common.enums.BusinessErrorCode;

import java.io.Serial;

@Getter
public class BusinessException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final BusinessErrorCode businessErrorCode;
    private final Object[] arguments;

    public BusinessException(BusinessErrorCode businessErrorCode) {
        super(businessErrorCode.message());
        this.businessErrorCode = businessErrorCode;
        this.arguments = null;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, Object... arguments) {
        super(businessErrorCode.message());
        this.businessErrorCode = businessErrorCode;
        this.arguments = new Object[arguments.length];
        System.arraycopy(arguments, 0, this.arguments, 0, arguments.length);
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String message) {
        super(message);
        this.businessErrorCode = businessErrorCode;
        this.arguments = null;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String message, Object... arguments) {
        super(message);
        this.businessErrorCode = businessErrorCode;
        this.arguments = new Object[arguments.length];
        System.arraycopy(arguments, 0, this.arguments, 0, arguments.length);
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String message, Throwable cause) {
        super(message, cause);
        this.businessErrorCode = businessErrorCode;
        this.arguments = null;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, String message, Throwable cause, Object... arguments) {
        super(message, cause);
        this.businessErrorCode = businessErrorCode;
        this.arguments = new Object[arguments.length];
        System.arraycopy(arguments, 0, this.arguments, 0, arguments.length);
    }

    public BusinessException(BusinessErrorCode businessErrorCode, Throwable cause) {
        super(cause);
        this.businessErrorCode = businessErrorCode;
        this.arguments = null;
    }

    public BusinessException(BusinessErrorCode businessErrorCode, Throwable cause, Object... arguments) {
        super(cause);
        this.businessErrorCode = businessErrorCode;
        this.arguments = new Object[arguments.length];
        System.arraycopy(arguments, 0, this.arguments, 0, arguments.length);
    }
}
