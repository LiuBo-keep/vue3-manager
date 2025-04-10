package org.example.vue3manager.common.exception;

/**
 * 自定义异常类AuthException，用于处理身份验证相关的异常
 * 继承自Exception类，提供了一个带有错误消息参数的构造函数
 */
public class AuthException extends Exception {


  /**
   * 构造函数，初始化异常对象时接收一个错误消息字符串
   *
   * @param message 错误消息，描述验证失败的原因
   */
  public AuthException(String message) {
    super(message);
  }
}
