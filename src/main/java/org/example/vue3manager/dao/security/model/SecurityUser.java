package org.example.vue3manager.dao.security.model;

import java.time.LocalDateTime;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * SecurityUser类代表系统中的安全用户实体
 * 它包含了用户的基本信息和安全相关属性
 * 该类被标记为@Data，表示它是一个简单的数据载体，拥有标准的getter和setter方法
 *
 * @author aidan.liu
 */
@Data
@Alias("SecurityUser")
public class SecurityUser {

  /**
   * 用户唯一标识符
   * 用于唯一地识别每个用户
   */
  private String id;

  /**
   * 用户名
   * 通常是用户登录时使用的名称
   */
  private String name;

  /**
   * 用户密码
   * 用于用户身份验证
   */
  private String password;

  /**
   * 密码过期时间
   * 用于记录密码的过期时间
   */
  private LocalDateTime passwordExpirationTime;

  /**
   * 临时密码
   * 用于记录用户是否使用了临时密码
   */
  private Boolean temporaryPassword;

  /**
   * 用户联系电话
   * 用于联系用户
   */
  private String phone;

  /**
   * 用户电子邮件地址
   * 用于用户注册、找回密码等
   */
  private String email;

  /**
   * 用户类型
   * 用于区分普通用户和管理员等用户类型
   */
  private String userType;

  /**
   * 用户状态
   * 用于表示用户账户是否被禁用、锁定等
   */
  private String status;

  /**
   * 用户头像路径
   * 用于显示用户的头像
   */
  private String avatarPath;

  /**
   * 用户创建时间
   * 记录用户账户创建的时间
   */
  private LocalDateTime createTime;

  /**
   * 用户创建者
   * 记录创建该用户账户的操作者
   */
  private String createBy;

  /**
   * 用户更新时间
   * 记录用户账户最后一次更新的时间
   */
  private LocalDateTime updateTime;

  /**
   * 用户更新者
   * 记录最后一次更新该用户账户的操作者
   */
  private String updateBy;
}

