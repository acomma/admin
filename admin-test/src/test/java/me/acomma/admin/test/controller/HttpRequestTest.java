package me.acomma.admin.test.controller;

import me.acomma.admin.common.Result;
import me.acomma.admin.common.dto.user.UserLoginDTO;
import me.acomma.admin.common.enums.SystemErrorCode;
import me.acomma.admin.common.vo.menu.MenuVO;
import me.acomma.admin.common.vo.user.UserLoginVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
public class HttpRequestTest {
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String token;

    @BeforeEach
    void beforeEach() {
        UserLoginDTO dto = UserLoginDTO.builder().username("admin").password("123456").build();

        RequestEntity<UserLoginDTO> request = RequestEntity.post("http://localhost:" + port + "/auth/login").body(dto);

        ResponseEntity<Result<UserLoginVO>> response = testRestTemplate.exchange(request, new ParameterizedTypeReference<>() {
        });

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertNotNull(response.getBody().getData());

        token = response.getBody().getData().getToken();
    }

    @Test
    @DisplayName("获取菜单")
    void test1() {
        RequestEntity<Void> request = RequestEntity.get("http://localhost:" + port + "/menus/1000")
                .header("Token", token)
                .build();

        ResponseEntity<Result<MenuVO>> response = testRestTemplate.exchange(
                request,
                new ParameterizedTypeReference<>() {
                }
        );

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Result<MenuVO> result = response.getBody();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(SystemErrorCode.SUCCESS.code(), result.getCode());
        Assertions.assertEquals(1000, result.getData().getId());
    }
}
