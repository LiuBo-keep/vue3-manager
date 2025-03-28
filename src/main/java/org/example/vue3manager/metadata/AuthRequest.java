package org.example.vue3manager.metadata;

import lombok.Data;

/**
 * 表示用户认证请求的类，封装了用户认证所需的数据。
 *
 * @author aidan.liu
 */
@Data
public class AuthRequest {

  /**
   * 用户名，用于身份验证。
   */
  private String name;

  /**
   * 密码，用于身份验证。
   */
  private String password;
}
