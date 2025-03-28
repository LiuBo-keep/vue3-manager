package org.example.vue3manager.common.exception.handler;

import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.common.response.Response;
import org.example.vue3manager.common.response.ResponseCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理类
 * 用于捕获和处理全局范围内的异常，以统一的响应方式返回给客户端
 *
 * @author aidan.liu
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * 处理认证异常
   * 当控制器方法抛出认证异常时，此方法将捕获并处理它
   *
   * @param ex 认证异常对象
   * @return 响应对象，包含错误代码和异常消息
   */
  @ResponseBody
  @ExceptionHandler(AuthException.class)
  public final Response handleAuthException(AuthException ex) {
    return Response.fail().code(ResponseCode.UNAUTHORIZED)
        .msg(ex.getMessage());
  }
}
