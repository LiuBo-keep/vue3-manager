package org.example.vue3manager.core.auth;

import org.example.vue3manager.core.auth.context.SecurityIdentity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class AuthConfig {

    @Bean
    @RequestScope
    public SecurityIdentity getSecurityIdentity() {
        return new SecurityIdentity();
    }
}
