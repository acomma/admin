package me.acomma.admin.common.enums;

public enum UserErrorCode implements BusinessErrorCode {
    USER_EXIST(1, "用户已经存在"),

    USER_NOT_EXIST(2, "用户不存在");

    private final Integer number;

    private final String message;

    UserErrorCode(Integer number, String message) {
        this.number = number;
        this.message = message;
    }

    @Override
    public Integer number() {
        return number;
    }

    @Override
    public Module module() {
        return Module.USER;
    }

    @Override
    public String message() {
        return message;
    }
}
