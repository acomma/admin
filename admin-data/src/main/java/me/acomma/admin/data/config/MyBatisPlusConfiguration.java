package me.acomma.admin.data.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("me.acomma.admin.data.mapper")
public class MyBatisPlusConfiguration {
}
