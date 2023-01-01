package me.acomma.admin.web.security;

import me.acomma.admin.common.constant.RoleConstant;
import me.acomma.admin.common.constant.UserConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public abstract class SecurityUtils {
    public static LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (LoginUser) authentication.getPrincipal();
    }

    public static Long getUserId() {
        LoginUser loginUser = getLoginUser();
        return loginUser.getUserId();
    }

    public static String getUsername() {
        LoginUser loginUser = getLoginUser();
        return loginUser.getUsername();
    }

    public static boolean isSystemAdministratorUser() {
        return Objects.equals(UserConstant.SYSTEM_ADMINISTRATOR_USER_ID, getUserId())
                || Objects.equals(UserConstant.SYSTEM_ADMINISTRATOR_USER_USERNAME, getUsername());
    }

    public static boolean isSystemAdministratorUser(Long userId) {
        return Objects.equals(UserConstant.SYSTEM_ADMINISTRATOR_USER_ID, userId);
    }

    public static boolean isSystemAdministratorRole(Long roleId) {
        return Objects.equals(RoleConstant.SYSTEM_ADMINISTRATOR_ROLE_ID, roleId);
    }
}
