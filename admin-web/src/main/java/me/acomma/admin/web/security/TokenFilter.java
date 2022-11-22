package me.acomma.admin.web.security;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.core.manager.TokenManager;
import me.acomma.admin.core.service.UserService;
import me.acomma.admin.data.model.po.UserPO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private final TokenManager tokenManager;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Token");
        // 未获取到 Token 时交给后面的过滤器，由它们决定是放行还是需要登录
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        Long userId = tokenManager.getUserIdByToken(token);
        // 未从 Redis 中获取到 Token，则表示用户未登录或者会话已过期，交给后面的过滤器，由它们决定是放行还是需要登录
        if (Objects.isNull(userId)) {
            filterChain.doFilter(request, response);
            return;
        }

        UserPO user = userService.getById(userId);
        // 用户信息不存在时交给后面的过滤器，由它们决定是放行还是需要登录
        if (Objects.isNull(user)) {
            filterChain.doFilter(request, response);
            return;
        }

        LoginUser loginUser = LoginUser.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
