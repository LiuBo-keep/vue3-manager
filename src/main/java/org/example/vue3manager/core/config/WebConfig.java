package org.example.vue3manager.core.config;

import org.example.vue3manager.core.auth.filter.AuthFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthFilter authFilter;

    public WebConfig(AuthFilter authFilter) {
        this.authFilter = authFilter;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许所有路径
                .allowedOrigins("*") // 允许所有来源
                .allowedMethods("*") // 允许的 HTTP 方法
                .allowedHeaders("*") // 允许的请求头
                .allowCredentials(false) // 是否允许发送 Cookie
                .maxAge(3600); // 预检请求的有效期
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authFilter)
                .addPathPatterns("/**") // 拦截所有路径
                .excludePathPatterns("/auth/**"); // 排除某些路径
    }
}