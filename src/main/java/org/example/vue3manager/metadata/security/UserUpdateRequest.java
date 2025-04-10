package org.example.vue3manager.metadata.security;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserUpdateRequest {

  /**
   * 用户名
   * 创建用户时必须提供此字段
   */
  @NotNull(message = "用户名称不能为空")
  @Pattern(regexp = "^[a-z0-9]{3,20}$", message = "用户名称必须是6~20数字或字母组成")
  private String name;

  /**
   * 用户密码
   * 创建用户时必须提供此字段
   */
  @NotNull(message = "密码不能为空")
  @Pattern(regexp = "^[a-z0-9]{3,}$", message = "密码必须是3位以上数字或字母组成")
  private String password;

  /**
   * 用户邮箱
   * 可选字段，用于用户的联系和信息通知
   */
  private String email;

  /**
   * 用户电话号码
   * 可选字段，用于用户的联系和信息通知
   */
  @Max(value = 11,message = "手机号码长度必须是11位")
  private String phone;

  /**
   * 用户头像的URL
   * 可选字段，用于展示用户的个性化信息
   */
  private byte[] avatar;
}
