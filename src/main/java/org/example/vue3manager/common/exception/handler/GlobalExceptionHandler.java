package org.example.vue3manager.common.exception.handler;

import java.util.List;
import java.util.Map;
import org.example.vue3manager.common.exception.AuthException;
import org.example.vue3manager.common.exception.BusinessException;
import org.example.vue3manager.common.response.Response;
import org.example.vue3manager.common.response.ResponseCode;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
  public Response handleAuthException(AuthException ex) {
    return Response.fail().code(ResponseCode.UNAUTHORIZED)
        .msg(ex.getMessage());
  }

  /**
   * 处理业务异常
   * 当控制器方法抛出业务异常时，此方法将捕获并处理它
   *
   * @param ex 业务异常对象
   * @return 响应对象，包含错误代码和异常消息
   */
  @ResponseBody
  @ExceptionHandler(BusinessException.class)
  public Response handleAuthException(BusinessException ex) {
    return Response.fail().code(ResponseCode.BAD_REQUEST)
        .msg(ex.getMessage());
  }

  /**
   * 处理参数验证异常
   */
  @ResponseBody
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Response handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
    return Response.fail().code(ResponseCode.BAD_REQUEST).msg(allErrors.getFirst().getDefaultMessage());
  }
}
