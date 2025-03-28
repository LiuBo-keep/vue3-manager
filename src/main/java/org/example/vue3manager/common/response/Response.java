package org.example.vue3manager.common.response;


import lombok.Data;

/**
 * Response 类用于封装返回给客户端的响应信息。
 * 它提供响应状态代码、消息和数据等基本信息。
 *
 * @author aidan.liu
 */
@Data
public class Response {

  /**
   * 响应状态码，指示响应的结果。
   */
  protected ResponseCode code;

  /**
   * 响应消息，提供关于响应的更多详细信息。
   */
  protected String msg;

  /**
   * 响应数据，携带响应的实际内容。
   */
  protected Object data;


  /**
   * 创建一个用于构建成功响应的ResponseBuilder实例。
   *
   * @return 返回一个ResponseBuilder实例，用于链式调用后续操作。
   */
  public static ResponseBuilder ok() {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setCode(ResponseCode.SUCCESS);
    responseBuilder.setMsg(ResponseCode.SUCCESS.getDescription());
    return responseBuilder;
  }


  /**
   * 创建一个用于构建失败响应的ResponseBuilder实例。
   *
   * @return 返回一个ResponseBuilder实例，用于链式调用后续操作。
   */
  public static ResponseBuilder fail() {
    ResponseBuilder responseBuilder = new ResponseBuilder();
    responseBuilder.setCode(ResponseCode.BAD_REQUEST);
    responseBuilder.setMsg(ResponseCode.BAD_REQUEST.getDescription());
    return responseBuilder;
  }
}
