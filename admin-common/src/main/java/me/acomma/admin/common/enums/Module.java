package me.acomma.admin.common.enums;

public enum Module {
    /**
     * 用户模块。
     */
    USER(10),

    /**
     * 角色模块。
     */
    ROLE(11),

    /**
     * 菜单模块。
     */
    MENU(12);

    /**
     * 模块编码，取值范围为 10 ~ 99
     */
    private final Integer code;

    Module(Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
