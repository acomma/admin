package me.acomma.admin.web.security;

import lombok.RequiredArgsConstructor;
import me.acomma.admin.core.service.UserService;
import me.acomma.admin.data.po.UserPO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO user = userService.getByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码不存在");
        }

        return LoginUser.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
