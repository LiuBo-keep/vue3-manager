package org.example.vue3manager.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.vue3manager.dao.model.SecurityUser;

@Mapper
public interface SecurityUserMapper {
  SecurityUser insert(SecurityUser securityUser);
}
