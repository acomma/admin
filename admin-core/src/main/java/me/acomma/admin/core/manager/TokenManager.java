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

    private final String AUTH_TOKEN_PREFIX = "auth:token:";
    private final String AUTH_USER_PREFIX = "auth:user:";

    public String createToken(Long userId) {
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        stringRedisTemplate.opsForValue().set(AUTH_TOKEN_PREFIX + token, userId.toString(), 7, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(AUTH_USER_PREFIX + userId, token, 7, TimeUnit.DAYS);

        return token;
    }

    public void removeToken(Long userId) {
        String token = stringRedisTemplate.opsForValue().get(AUTH_USER_PREFIX + userId);

        stringRedisTemplate.delete(AUTH_USER_PREFIX + userId);
        stringRedisTemplate.delete(AUTH_TOKEN_PREFIX + token);
    }

    public Long getUserIdByToken(String token) {
        String userId = stringRedisTemplate.opsForValue().get(AUTH_TOKEN_PREFIX + token);
        if (!StringUtils.hasText(userId)) {
            return null;
        }
        return Long.valueOf(userId);
    }
}
