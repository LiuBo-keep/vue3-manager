package org.example.vue3manager.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;
import org.example.vue3manager.common.auth.AuthType;
import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.metadata.AuthRequest;
import org.example.vue3manager.metadata.AuthResponse;
import org.example.vue3manager.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
  @Override
  public AuthResponse auth(AuthRequest authRequest) throws AuthException {
    if (!authRequest.getName().equalsIgnoreCase("")) {
      throw new AuthException("Wrong username or password！");
    }
    return new AuthResponse(AuthType.BEARER.getType(), UUID.randomUUID().toString(), LocalDateTime.now().toString());
  }
}
