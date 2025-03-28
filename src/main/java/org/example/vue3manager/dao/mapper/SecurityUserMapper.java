package org.example.vue3manager.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.vue3manager.dao.model.SecurityUser;

@Mapper
public interface SecurityUserMapper {

  @Insert(
      "INSERT INTO security_user(id, name, password, create_time, createby)"
          + " VALUES (#{securityUser.id}, #{securityUser.name},#{securityUser.password},"
          + " #{securityUser.createTime}, #{securityUser.createBy})")
  void insert(@Param("securityUser") SecurityUser securityUser);

  @Select("SELECT id,name,password,phone,email,create_time,createBy,update_time,updateBy FROM security_user WHERE name = #{name}")
  SecurityUser findByName(@Param("name") String name);
}
