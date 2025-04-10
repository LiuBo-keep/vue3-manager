package org.example.vue3manager.service.security.impl;

import java.time.LocalDateTime;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.example.vue3manager.common.exception.BusinessException;
import org.example.vue3manager.core.auth.context.SecurityIdentity;
import org.example.vue3manager.core.oss.EasyOss;
import org.example.vue3manager.core.snowflake.SnowflakeIdGenerator;
import org.example.vue3manager.dao.security.SecurityUserDao;
import org.example.vue3manager.dao.security.model.SecurityUser;
import org.example.vue3manager.metadata.security.FindCurrentLoginUserInfoResponse;
import org.example.vue3manager.metadata.security.UserCreateRequest;
import org.example.vue3manager.service.security.UserService;
import org.example.vue3manager.service.security.constant.UserType;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final SecurityUserDao securityUserDao;
  private final SecurityIdentity securityIdentity;
  private final SnowflakeIdGenerator snowflakeIdGenerator;

  public UserServiceImpl(SecurityIdentity securityIdentity, SecurityUserDao securityUserDao,
                         SnowflakeIdGenerator snowflakeIdGenerator) {
    this.securityUserDao = securityUserDao;
    this.securityIdentity = securityIdentity;
    this.snowflakeIdGenerator = snowflakeIdGenerator;
  }

  @Override
  public FindCurrentLoginUserInfoResponse findCurrentLoginUserInfo() {
    String username = securityIdentity.getUsername();
    FindCurrentLoginUserInfoResponse response = new FindCurrentLoginUserInfoResponse();
    response.setName(username);
    response.setEmail(securityIdentity.getEmail());
    response.setPhone(securityIdentity.getPhone());
    response.setPasswordExpireTime(securityIdentity.getPasswordExpireTime());
    response.setTemporaryPassword(securityIdentity.getTemporaryPassword());
    response.setUserType(securityIdentity.getUserType());
    response.setStatus(securityIdentity.getStatus());
    response.setAvatarPath(securityIdentity.getAvatarPath());
    return response;
  }

  @Override
  public void createUser(UserCreateRequest userCreateRequest) throws BusinessException {
    SecurityUser securityUser = securityUserDao.findByName(userCreateRequest.getName());
    if (Objects.nonNull(securityUser)) {
      throw new BusinessException("用户已存在");
    }
    securityUser = new SecurityUser();
    securityUser.setId(snowflakeIdGenerator.nextIdStr());
    securityUser.setName(userCreateRequest.getName());
    securityUser.setPassword(userCreateRequest.getPassword());
    if (StringUtils.isBlank(userCreateRequest.getUserType())) {
      securityUser.setUserType(UserType.NORMAL);
    } else {
      securityUser.setUserType(userCreateRequest.getUserType());
    }
    securityUser.setEmail(userCreateRequest.getEmail());
    securityUser.setPhone(userCreateRequest.getPhone());
    securityUser.setCreateBy(securityIdentity.getUsername());
    securityUser.setCreateTime(LocalDateTime.now());
    securityUserDao.insert(securityUser);
  }

  @Override
  public void updateUser() {

  }
}
