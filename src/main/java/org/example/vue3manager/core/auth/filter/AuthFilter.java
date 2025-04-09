package org.example.vue3manager.core.auth.filter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.core.auth.constant.AuthFlag;
import org.example.vue3manager.core.auth.constant.AuthType;
import org.example.vue3manager.core.auth.context.SecurityIdentity;
import org.example.vue3manager.core.auth.jwttoken.JwtToken;
import org.example.vue3manager.core.auth.jwttoken.JwtTokenManager;
import org.example.vue3manager.dao.security.SecurityKeyDao;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthFilter implements HandlerInterceptor {

  private static final String RELEASE_PATH = "/auth/token";

  private final SecurityKeyDao securityKeyDao;
  private final JwtTokenManager jwtTokenManager;
  private final SecurityIdentity securityIdentity;

  public AuthFilter(JwtTokenManager jwtTokenManager, SecurityIdentity securityIdentity, SecurityKeyDao securityKeyDao) {
    this.securityKeyDao = securityKeyDao;
    this.jwtTokenManager = jwtTokenManager;
    this.securityIdentity = securityIdentity;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // 放行登录接口
    if (request.getRequestURI().contains(RELEASE_PATH)) {
      return true;
    }

    String authHeader = request.getHeader(AuthFlag.authHeader);

    if (authHeader == null || !authHeader.startsWith(AuthType.BEARER.getType())) {
      throw new AuthException("Missing or invalid Authorization header");
    }

    String token = authHeader.substring(7); // The part after "Bearer "
    String accessTokenKey = securityKeyDao.findAccessTokenKey();
    if (!jwtTokenManager.validateToken(token, accessTokenKey)) {
      throw new AuthException("Invalid token");
    }

    JwtToken jwtToken = jwtTokenManager.parseToken(token);
    securityIdentity.setUsername(jwtToken.getSubject());
    return true;
  }
}
