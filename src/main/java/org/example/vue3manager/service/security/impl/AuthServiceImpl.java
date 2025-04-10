package org.example.vue3manager.service.security.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.commons.lang3.StringUtils;
import org.example.vue3manager.core.auth.constant.AuthFlag;
import org.example.vue3manager.core.auth.constant.AuthType;
import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.core.auth.jwttoken.JwtToken;
import org.example.vue3manager.core.auth.jwttoken.JwtTokenManager;
import org.example.vue3manager.dao.security.SecurityKeyDao;
import org.example.vue3manager.dao.security.SecurityUserDao;
import org.example.vue3manager.dao.security.model.SecurityUser;
import org.example.vue3manager.metadata.security.AuthRequest;
import org.example.vue3manager.metadata.security.AuthResponse;
import org.example.vue3manager.service.security.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

  private final SecurityKeyDao securityKeyDao;
  private final SecurityUserDao securityUserDao;
  private final JwtTokenManager jwtTokenManager;

  public AuthServiceImpl(SecurityUserDao securityUserDao, JwtTokenManager jwtTokenManager,
                         SecurityKeyDao securityKeyDao) {
    this.securityKeyDao = securityKeyDao;
    this.securityUserDao = securityUserDao;
    this.jwtTokenManager = jwtTokenManager;
  }

  @Override
  public AuthResponse auth(AuthRequest authRequest) throws AuthException {
    String grantType = authRequest.getGrantType();
    if (grantType == null) {
      throw new AuthException("授权类型不能为空!");
    }

    return switch (grantType.toLowerCase()) {
      case "password" -> validatePasswordAuth(authRequest);
      case "refresh_token" -> validateRefreshTokenAuth(authRequest);
      case "client_credentials" -> throw new AuthException("客户端凭证认证尚未实现!");
      default -> {
        logger.info("不支持的授权类型: {}", grantType);
        throw new AuthException("不支持的授权类型!");
      }
    };
  }

  private AuthResponse validatePasswordAuth(AuthRequest authRequest) throws AuthException {
    if (StringUtils.isBlank(authRequest.getName())) {
      throw new AuthException("用户名不能为空!");
    }
    if (StringUtils.isBlank(authRequest.getPassword())) {
      throw new AuthException("密码不能为空!");
    }

    SecurityUser securityUser = securityUserDao.findByName(authRequest.getName());
    if (securityUser == null) {
      throw new AuthException("用户名或密码错误!");
    }

    if (!securityUser.getPassword().equals(authRequest.getPassword())) {
      throw new AuthException("用户名或密码错误!");
    }

    return generateTokens(securityUser.getName());
  }

  private AuthResponse validateRefreshTokenAuth(AuthRequest authRequest) throws AuthException {
    if (StringUtils.isBlank(authRequest.getRefreshToken())) {
      throw new AuthException("刷新令牌不能为空!");
    }

    // 验证token
    String refreshTokenKey = securityKeyDao.findRefreshTokenKey();
    if (!jwtTokenManager.validateToken(authRequest.getRefreshToken(), refreshTokenKey)) {
      logger.info("无效的刷新令牌");
      throw new AuthException("无效的刷新令牌!");
    }

    JwtToken jwtToken = jwtTokenManager.parseToken(authRequest.getRefreshToken());
    String userName = jwtToken.getSubject();
    if (userName == null) {
      logger.info("无效的刷新令牌");
      throw new AuthException("无效的刷新令牌!");
    }

    SecurityUser securityUser = securityUserDao.findByName(userName);
    if (securityUser == null) {
      throw new AuthException("用户名或密码错误!");
    }

    return generateTokens(securityUser.getName());
  }

  private AuthResponse generateTokens(String userName) {
    LocalDateTime now = LocalDateTime.now();
    return new AuthResponse(
        AuthType.BEARER.getType(),
        jwtTokenManager.generateAccessToken(userName),
        now.plusSeconds(AuthFlag.ACCESS_TOKEN_EXPIRATION).toEpochSecond(ZoneOffset.UTC),
        jwtTokenManager.generateRefreshToken(userName),
        now.plusSeconds(AuthFlag.REFRESH_TOKEN_EXPIRATION).toEpochSecond(ZoneOffset.UTC));
  }
}
