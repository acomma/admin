package me.acomma.admin.common.enums;

public enum MenuErrorCode implements BusinessErrorCode {
    MENU_EXIST(1, "菜单已经存在"),

    MENU_NOT_EXIST(2, "菜单不存在"),

    PARENT_MENU_NOT_EXIST(3, "上级菜单不存在");

    private final Integer number;

    private final String message;

    MenuErrorCode(Integer number, String message) {
        this.number = number;
        this.message = message;
    }

    @Override
    public Integer number() {
        return number;
    }

    @Override
    public Module module() {
        return Module.MENU;
    }

    @Override
    public String message() {
        return message;
    }
}
