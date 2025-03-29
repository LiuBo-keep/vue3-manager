package org.example.vue3manager.core.auth.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.core.auth.constant.AuthFlag;
import org.example.vue3manager.core.auth.constant.AuthType;
import org.example.vue3manager.core.auth.jwttoken.JwtTokenManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthFilter implements HandlerInterceptor {

    private JwtTokenManager jwtTokenManager;

    public AuthFilter(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(AuthFlag.authHeader);

        if (authHeader == null || !authHeader.startsWith(AuthType.BEARER.getType())) {
            throw new AuthException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // The part after "Bearer "
        if (jwtTokenManager.validateToken(token)) {
            throw new AuthException("Invalid token");
        }

        // Token is valid, proceed with the request
        return true;
    }
}
