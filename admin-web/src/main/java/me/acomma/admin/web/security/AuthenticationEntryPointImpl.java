package me.acomma.admin.web.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.acomma.admin.common.Result;
import me.acomma.admin.common.enums.SystemErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public AuthenticationEntryPointImpl() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        SystemErrorCode errorCode = SystemErrorCode.AUTHENTICATION_FAILED;
        String message = "用户未登录或会话已过期";
        Result<Void> result = Result.<Void>builder().code(errorCode.code()).message(message).build();
        String content = objectMapper.writeValueAsString(result);

        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        // MediaType.APPLICATION_JSON_UTF8_VALUE 已被标记为 Deprecated
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
        response.getWriter().write(content);
    }
}
