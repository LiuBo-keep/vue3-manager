package org.example.vue3manager.core.auth.context;

import java.time.LocalDateTime;
import lombok.Data;
import org.example.vue3manager.dao.security.model.SecurityUser;

/**
 * SecurityIdentity类代表了一个请求范围内的安全身份对象
 * 这个类存储了用户的安全相关信息，如用户名、密码、联系方式等，并提供了一个初始化这些信息的方法
 *
 * @author aidan.liu
 */
@Data
public class SecurityIdentity {
  private String username;
  private String password;
  private LocalDateTime passwordExpireTime;
  private Boolean temporaryPassword;
  private String phone;
  private String email;
  private String userType;
  private String status;
  private String avatarPath;


  /**
   * 初始化SecurityIdentity对象的方法
   * 该方法复制SecurityUser对象的属性值到SecurityIdentity对象中，便于在新的请求中重建用户安全身份
   *
   * @param securityUser SecurityUser对象，包含用户的安全信息
   */
  public void initSecurityIdentity(SecurityUser securityUser) {
    this.setUsername(securityUser.getName());
    this.setPassword(securityUser.getPassword());
    this.setPasswordExpireTime(securityUser.getPasswordExpirationTime());
    this.setTemporaryPassword(securityUser.getTemporaryPassword());
    this.setPhone(securityUser.getPhone());
    this.setEmail(securityUser.getEmail());
    this.setUserType(securityUser.getUserType());
    this.setStatus(securityUser.getStatus());
    this.setAvatarPath(securityUser.getAvatarPath());
  }
}
