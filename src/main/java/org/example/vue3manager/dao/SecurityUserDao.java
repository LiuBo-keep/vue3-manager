package org.example.vue3manager.dao;

import org.example.vue3manager.dao.mapper.SecurityUserMapper;
import org.example.vue3manager.dao.model.SecurityUser;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityUserDao {

  private final SecurityUserMapper securityUserMapper;

  public SecurityUserDao(SecurityUserMapper securityUserMapper) {
    this.securityUserMapper = securityUserMapper;
  }

  public SecurityUser insert(SecurityUser securityUser) {
    securityUserMapper.insert(securityUser);
    return securityUser;
  }
}
