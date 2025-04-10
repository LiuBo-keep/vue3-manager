package org.example.vue3manager.common.exception;

/**
 * 自定义业务异常类，继承自Exception
 * 用于在业务逻辑中表示和处理异常情况
 * 该类允许在抛出异常时提供详细的错误信息
 */
public class BusinessException extends Exception {

  /**
   * 构造函数，初始化业务异常对象
   *
   * @param message 异常信息，描述发生错误的详细情况
   */
  public BusinessException(String message) {
    super(message);
  }
}
