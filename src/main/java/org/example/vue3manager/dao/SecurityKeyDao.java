package org.example.vue3manager.dao;

import org.example.vue3manager.dao.mapper.SecurityKeyMapper;
import org.example.vue3manager.dao.model.SecurityKey;
import org.springframework.stereotype.Repository;

/**
 * SecurityKeyDao类是一个数据访问对象(DAO)，用于处理与SecurityKey相关的数据库操作
 * 它充当应用程序与数据库之间的中间层，提供对SecurityKey实体的插入和查询功能
 *
 * @author aidan.liu
 */
@Repository
public class SecurityKeyDao {

  /**
   * SecurityKeyMapper是一个接口，用于定义与SecurityKey表相关的SQL操作
   * 通过构造函数注入，它允许SecurityKeyDao执行数据库操作
   */
  private final SecurityKeyMapper securityKeyMapper;

  /**
   * 构造函数，用于初始化SecurityKeyDao类
   *
   * @param securityKeyMapper 一个SecurityKeyMapper实例，用于执行数据库操作
   */
  public SecurityKeyDao(SecurityKeyMapper securityKeyMapper) {
    this.securityKeyMapper = securityKeyMapper;
  }

  /**
   * 插入一个新的SecurityKey到数据库中
   *
   * @param securityKey 要插入的SecurityKey对象
   * @return 插入后的SecurityKey对象
   */
  public SecurityKey insert(SecurityKey securityKey) {
    securityKeyMapper.insert(securityKey);
    return securityKey;
  }

  /**
   * 查询SecurityKey表中的密钥
   *
   * @return 查询到的SecurityKey对象，如果没有找到则返回null
   */
  public SecurityKey findKey() {
    return securityKeyMapper.findKey();
  }

  /**
   * 获取公钥字符串
   *
   * <p>此方法用于从SecurityKeyMapper中获取公钥信息
   * 主要目的是为了提供一个接口，让其他部分的代码可以轻松访问公钥
   *
   * @return String 返回公钥的字符串表示
   */
  public String findPublicKey() {
    return securityKeyMapper.findPublicKey();
  }

  /**
   * 获取私钥字符串
   *
   * <p>此方法用于从SecurityKeyMapper中获取私钥信息
   * 主要目的是为了提供一个接口，让其他部分的代码可以轻松访问私钥
   *
   * @return String 返回私钥的字符串表示
   */
  public String findPrivateKey() {
    return securityKeyMapper.findPrivateKey();
  }

  public String findAccessTokenKey() {
    return securityKeyMapper.findAccessTokenKey();
  }

  public String findRefreshTokenKey() {
    return securityKeyMapper.findRefreshTokenKey();
  }
}

