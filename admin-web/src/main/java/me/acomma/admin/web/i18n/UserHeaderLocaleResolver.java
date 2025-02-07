package me.acomma.admin.web.i18n;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.i18n.AbstractLocaleResolver;

import java.util.Locale;

/**
 * 参考 {@link org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver} 实现，从请求头 {@code X-User-Language} 获取 {@code Locale}，支持 {@code zh_CN}、{@code en_US} 等格式。
 */
public class UserHeaderLocaleResolver extends AbstractLocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale defaultLocale = getDefaultLocale();
        if (defaultLocale != null && request.getHeader("X-User-Language") == null) {
            return defaultLocale;
        }
        return Locale.of(request.getHeader("X-User-Language"));
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        throw new UnsupportedOperationException("Cannot change HTTP X-User-Language header - use a different locale resolution strategy");
    }
}
