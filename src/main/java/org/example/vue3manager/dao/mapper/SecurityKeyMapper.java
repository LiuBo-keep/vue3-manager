package org.example.vue3manager.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.vue3manager.dao.model.SecurityKey;

/**
 * SecurityKeyMapper接口用于定义与security_key表交互的方法
 * 它包括插入新密钥对和查找密钥对的方法
 *
 * @author aidan.liu
 */
@Mapper
public interface SecurityKeyMapper {

  /**
   * 插入一个新的安全密钥对到数据库中
   * 此方法定义了一个SQL插入语句，用于将SecurityKey对象中的数据插入到security_key表中
   *
   * @param securityKey 要插入的安全密钥对象，包含密钥对的所有必要信息
   */
  @Insert(
      "insert into security_key(id,public_key, private_key, access_token_key,refresh_token_key,create_time, createby, update_time, updateby) "
          + "values(#{securityKey.id},#{securityKey.publicKey}, #{securityKey.privateKey}, #{securityKey.accessTokenKey},"
          + " #{securityKey.refreshTokenKey},#{securityKey.createTime}, #{securityKey.createBy},  #{securityKey.updateTime}," +
          " #{securityKey.updateBy})")
  void insert(@Param("securityKey") SecurityKey securityKey);

  /**
   * 从数据库中查找一个安全密钥对
   * 此方法定义了一个SQL查询语句，用于从security_key表中选择所有列，并返回一个SecurityKey对象
   *
   * @return 返回找到的安全密钥对象，如果没有找到则返回null
   */
  @Select("select id,public_key, private_key,access_token_key,refresh_token_key, create_time,refresh_token_key,"
      + " createby, update_time, updateby from security_key")
  SecurityKey findKey();

  /**
   * 从数据库中获取公钥
   *
   * <p>此方法用于查询并返回存储在数据库中的公钥信息
   * 它使用预定义的SQL语句来选择名为"security_key"的表中的"public_key"列
   *
   * @return 字符串形式的公钥
   */
  @Select("select public_key from security_key")
  String findPublicKey();

  /**
   * 从数据库中获取私钥
   *
   * <p>此方法用于查询并返回存储在数据库中的私钥信息
   * 它使用预定义的SQL语句来选择名为"security_key"的表中的"private_key"列
   *
   * @return 字符串形式的私钥
   */
  @Select("select private_key from security_key")
  String findPrivateKey();

  @Select("select access_token_key from security_key")
  String findAccessTokenKey();

  @Select("select refresh_token_key from security_key")
  String findRefreshTokenKey();
}

