package org.example.vue3manager.dao.security.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.vue3manager.dao.security.model.SecurityUser;

@Mapper
public interface SecurityUserMapper {

  @Insert("INSERT INTO security_user(id, name, password,password_expiration_time,temporary_password,phone,email,user_type, "
          + "status,avatar_path,create_time, createby) VALUES (#{securityUser.id}, #{securityUser.name},#{securityUser.password},"
          + "#{securityUser.passwordExpirationTime},#{securityUser.temporaryPassword},#{securityUser.phone},#{securityUser.email},"
          + "#{securityUser.userType},#{securityUser.status},#{securityUser.avatarPath},#{securityUser.createTime}, #{securityUser.createBy})")
  void insert(@Param("securityUser") SecurityUser securityUser);

  @Select("SELECT id,name,password,password_expiration_time,temporary_password,phone,email,user_type,status,avatar_path,"
      + "create_time,createBy,update_time,updateBy FROM security_user WHERE name = #{name}")
  SecurityUser findByName(@Param("name") String name);
}
