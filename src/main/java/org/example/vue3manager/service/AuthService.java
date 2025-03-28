package org.example.vue3manager.service;

import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.metadata.AuthRequest;
import org.example.vue3manager.metadata.AuthResponse;

/**
 * 认证服务接口
 * 定义了系统中用于用户认证的服务
 *
 * @author aidan.liu
 */
public interface AuthService {

  /**
   * 执行用户认证
   *
   * <p>此方法接收一个认证请求对象，其中包含了用户认证所需的信息，如用户名和密码
   * 它负责调用底层认证机制来验证用户的身份，并返回一个认证响应对象，该对象包含了认证结果
   *
   * @param authRequest 认证请求对象，包含用户认证所需的信息
   * @return 认证响应对象，包含认证结果
   */
  public AuthResponse auth(AuthRequest authRequest) throws AuthException;
}
