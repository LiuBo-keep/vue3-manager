package org.example.vue3manager.common.response;

import lombok.Getter;

/**
 * ResponseCode枚举类定义了可能的响应状态码，每个状态码都有一个对应的整数值和描述信息
 *
 * @author aidan.liu
 */
@Getter
public enum ResponseCode {
  /**
   * 请求成功
   */
  SUCCESS(200, "请求成功"),

  /**
   * 客户端错误，请求参数错误
   */
  BAD_REQUEST(400, "客户端错误，请求参数错误"),

  /**
   * 未授权，需要身份验证
   */
  UNAUTHORIZED(401, "未授权，需要身份验证"),

  /**
   * 禁止访问，权限不足
   */
  FORBIDDEN(403, "禁止访问，权限不足"),

  /**
   * 资源未找到
   */
  NOT_FOUND(404, "资源未找到"),

  /**
   * 服务器内部错误
   */
  INTERNAL_SERVER_ERROR(500, "服务器内部错误");

  private final int code;
  private final String description;

  /**
   * 构造函数，用于初始化状态码及其描述信息
   *
   * @param code        状态码的整数值
   * @param description 状态码的描述信息
   */
  ResponseCode(int code, String description) {
    this.code = code;
    this.description = description;
  }
}
