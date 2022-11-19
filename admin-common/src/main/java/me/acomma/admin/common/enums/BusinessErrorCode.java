package me.acomma.admin.common.enums;

public interface BusinessErrorCode extends ErrorCode {
    /**
     * @return 错误编号，取值范围为 1 ~ 999
     */
    Integer number();

    /**
     * @return 错误码所属的模块
     */
    Module module();

    /**
     * @return 业务错误码，举个例子，模块编码为 35，错误编号为 78，则业务错误码为 35078
     */
    default Integer code() {
        return module().code() * 1000 + number();
    }
}
