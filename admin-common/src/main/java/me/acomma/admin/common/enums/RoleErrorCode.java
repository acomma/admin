package me.acomma.admin.common.enums;

public enum RoleErrorCode implements BusinessErrorCode {
    ROLE_EXIST(1, "角色已经存在"),

    ROLE_NOT_EXIST(2, "角色不存在");

    private final Integer number;

    private final String message;

    RoleErrorCode(Integer number, String message) {
        this.number = number;
        this.message = message;
    }

    @Override
    public Integer number() {
        return number;
    }

    @Override
    public Module module() {
        return Module.ROLE;
    }

    @Override
    public String message() {
        return message;
    }
}
