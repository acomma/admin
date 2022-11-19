package me.acomma.admin.common.enums;

public interface ErrorCode {
    /**
     * @return 错误码
     */
    Integer code();

    /**
     * @return 错误消息
     */
    String message();
}
