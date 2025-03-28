package org.example.vue3manager.metadata;

/**
 * 表示用户认证请求的类，封装了用户认证所需的数据。
 *
 * @author aidan.liu
 */
public class AuthRequest {

  /**
   * 用户名，用于身份验证。
   */
  private String name;

  /**
   * 密码，用于身份验证。
   */
  private String password;

  public AuthRequest() {
  }

  public AuthRequest(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
