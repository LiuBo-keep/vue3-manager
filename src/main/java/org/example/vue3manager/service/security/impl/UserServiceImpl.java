package org.example.vue3manager.service.security.impl;

import org.example.vue3manager.core.auth.context.SecurityIdentity;
import org.example.vue3manager.core.oss.EasyOss;
import org.example.vue3manager.metadata.security.FindCurrentLoginUserInfoResponse;
import org.example.vue3manager.service.security.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final SecurityIdentity securityIdentity;

  public UserServiceImpl(SecurityIdentity securityIdentity, EasyOss easyOss) {
    this.securityIdentity = securityIdentity;
  }

  @Override
  public FindCurrentLoginUserInfoResponse findCurrentLoginUserInfo() {
    String username = securityIdentity.getUsername();
    FindCurrentLoginUserInfoResponse response = new FindCurrentLoginUserInfoResponse();
    response.setName(username);
    return response;
  }
}
