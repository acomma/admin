package me.acomma.admin.web.controller;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.common.dto.user.UserLoginDTO;
import me.acomma.admin.common.vo.user.UserLoginVO;
import me.acomma.admin.core.manager.TokenManager;
import me.acomma.admin.web.security.LoginUser;
import me.acomma.admin.web.security.SecurityUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;

    @PostMapping("/user/login")
    public UserLoginVO login(@Validated @RequestBody UserLoginDTO dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String token = tokenManager.createToken(loginUser.getUserId());

        UserLoginVO vo = new UserLoginVO();
        vo.setUserId(loginUser.getUserId());
        vo.setToken(token);

        return vo;
    }

    @PostMapping("/user/logout")
    public void logout() {
        tokenManager.removeToken(SecurityUtils.getUserId());
    }
}
