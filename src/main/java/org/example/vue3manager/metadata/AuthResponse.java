package org.example.vue3manager.metadata;


/**
 * AuthResponse类用于封装认证响应信息
 * 它主要包含认证类型、认证令牌和令牌过期时间等信息
 *
 * @author aidan.liu
 */
public class AuthResponse {

  /**
   * 认证类型，例如Bearer或Basic等
   */
  private String type;

  /**
   * 认证令牌，用于访问受保护的资源
   */
  private String token;

  /**
   * 令牌过期时间，通常以时间戳的形式表示
   */
  private String expire;


  public AuthResponse() {
  }

  public AuthResponse(String type, String token, String expire) {
    this.type = type;
    this.token = token;
    this.expire = expire;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getExpire() {
    return expire;
  }

  public void setExpire(String expire) {
    this.expire = expire;
  }
}
