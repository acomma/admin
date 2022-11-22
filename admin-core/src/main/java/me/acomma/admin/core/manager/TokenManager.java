package me.acomma.admin.core.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TokenManager {
    private final StringRedisTemplate stringRedisTemplate;

    private final String LOGIN_TOKEN_PREFIX = "login:token:";
    private final String LOGIN_USER_PREFIX = "login:user:";

    public String createToken(Long userId) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        stringRedisTemplate.opsForValue().set(LOGIN_TOKEN_PREFIX + token, userId.toString(), 7, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(LOGIN_USER_PREFIX + userId, token, 7, TimeUnit.DAYS);

        return token;
    }

    public void removeToken(Long userId) {
        String token = stringRedisTemplate.opsForValue().get(LOGIN_USER_PREFIX + userId);

        stringRedisTemplate.delete(LOGIN_USER_PREFIX + userId);
        stringRedisTemplate.delete(LOGIN_TOKEN_PREFIX + token);
    }

    public Long getUserIdByToken(String token) {
        String userId = stringRedisTemplate.opsForValue().get(LOGIN_TOKEN_PREFIX + token);
        if (!StringUtils.hasText(userId)) {
            return null;
        }
        return Long.valueOf(userId);
    }
}
