package org.example.vue3manager.common.exception;


/**
 * 自定义异常类OssOperateException，用于处理OSS操作相关的异常
 * 继承自Exception类，提供了一个带有错误消息参数的构造函数
 */
public class OssOperateException extends Exception {

  /**
   * 构造函数，初始化异常对象时接收一个错误消息字符串
   *
   * @param message 错误消息，描述异常的信息
   */
  public OssOperateException(String message) {
    super(message);
  }
}
