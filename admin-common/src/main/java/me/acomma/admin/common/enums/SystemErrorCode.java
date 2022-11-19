package me.acomma.admin.common.enums;

public enum SystemErrorCode implements ErrorCode {
    SUCCESS(0, "操作成功"),

    SYSTEM_ERROR(1, "系统错误"),

    INVALID_PARAMETER(2, "参数无效"),

    ACCESS_DATABASE_FAILED(3, "访问数据库失败"),

    PROCESS_JSON_FAILED(4, "处理 JSON 数据失败");

    /**
     * 系统错误码，取值范围为 0 ~ 99
     */
    private final Integer code;

    private final String message;

    SystemErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
