package org.example.vue3manager.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.example.vue3manager.common.auth.AuthType;
import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.dao.SecurityUserDao;
import org.example.vue3manager.dao.model.SecurityUser;
import org.example.vue3manager.metadata.AuthRequest;
import org.example.vue3manager.metadata.AuthResponse;
import org.example.vue3manager.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private final SecurityUserDao securityUserDao;

  public AuthServiceImpl(SecurityUserDao securityUserDao) {
    this.securityUserDao = securityUserDao;
  }

  @Override
  public AuthResponse auth(AuthRequest authRequest) throws AuthException {
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

    return new AuthResponse(AuthType.BEARER.getType(), UUID.randomUUID().toString(), LocalDateTime.now().toString());
  }
}
