package org.example.vue3manager.metadata.security;

import lombok.Data;

/**
 * 表示用户认证请求的类，封装了用户认证所需的数据。
 *
 * @author aidan.liu
 */
@Data
public class AuthRequest {

  /**
   * password： 密码登录
   * refresh_token : 刷新令牌
   * client_credentials : 客户端凭证模式
   */
  private String grantType;

  /**
   * 用户名，用于身份验证。
   */
  private String name;

  /**
   * 密码，用于身份验证。
   */
  private String password;

  /**
   * 刷新令牌，用于获取新的访问令牌。
   */
  private String refreshToken;
}
