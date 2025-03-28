package org.example.vue3manager.dao;

import org.example.vue3manager.dao.mapper.SecurityUserMapper;
import org.example.vue3manager.dao.model.SecurityUser;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问对象（DAO）接口的实现类
 * 提供了对用户数据进行数据库操作的方法，如插入新用户和按用户名查找用户
 *
 * @author aidan.liu
 */
@Repository
public class SecurityUserDao {

  /**
   * 用户映射器接口的实现，用于执行用户相关的数据库操作
   */
  private final SecurityUserMapper securityUserMapper;

  /**
   * 构造方法，通过用户映射器初始化用户DAO
   *
   * @param securityUserMapper 用户映射器实例，用于数据库操作
   */
  public SecurityUserDao(SecurityUserMapper securityUserMapper) {
    this.securityUserMapper = securityUserMapper;
  }

  /**
   * 向数据库中插入一条新的用户记录
   * 此方法接收一个用户对象，通过映射器将其持久化到数据库中，并返回该用户对象
   *
   * @param securityUser 要插入的用户对象，包含用户的相关信息
   * @return 被插入的用户对象
   */
  public SecurityUser insert(SecurityUser securityUser) {
    securityUserMapper.insert(securityUser);
    return securityUser;
  }

  /**
   * 根据用户名从数据库中查找用户
   * 此方法接收一个用户名字符串，通过映射器从数据库中查找对应用户，并返回用户对象
   * 如果没有找到匹配的用户，返回null
   *
   * @param name 用户名，用于查找用户
   * @return 匹配用户名的用户对象，如果未找到则返回null
   */
  public SecurityUser findByName(String name) {
    return securityUserMapper.findByName(name);
  }
}

