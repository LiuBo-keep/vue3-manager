package org.example.vue3manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // 允许所有路径
        .allowedOrigins("*") // 允许所有来源
        .allowedMethods("*") // 允许的 HTTP 方法
        .allowedHeaders("*") // 允许的请求头
        .allowCredentials(false) // 是否允许发送 Cookie
        .maxAge(3600); // 预检请求的有效期
  }
}