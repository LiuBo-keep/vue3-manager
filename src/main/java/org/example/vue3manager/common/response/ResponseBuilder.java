package org.example.vue3manager.common.response;

import org.apache.commons.lang3.StringUtils;

/**
 * ResponseBuilder类用于构建响应对象它允许通过链式调用设置响应的各个属性，
 * 并最终构建出一个完整的Response实例
 *
 * @author aidan.liu
 */
public class ResponseBuilder extends Response {


  /**
   * 设置响应的数据
   *
   * @param data 响应的数据，可以是任意对象
   * @return 返回ResponseBuilder实例，支持链式调用
   */
  public ResponseBuilder entry(Object data) {
    this.data = data;
    return this;
  }


  /**
   * 设置响应的状态码
   *
   * @param code 响应的状态码，属于ResponseCode枚举类型
   * @return 返回ResponseBuilder实例，支持链式调用
   */
  public ResponseBuilder code(ResponseCode code) {
    this.code = code;
    return this;
  }


  /**
   * 设置响应的消息
   *
   * @param msg 响应的消息，描述响应的状态或错误信息
   * @return 返回ResponseBuilder实例，支持链式调用
   */
  public ResponseBuilder msg(String msg) {
    this.msg = msg;
    return this;
  }

  /**
   * 构建并返回一个完整的Response实例
   *
   * @return 返回一个包含状态码、消息和数据的Response实例
   */
  public Response build() {
    Response response = new Response();
    response.setCode(code);
    response.setMsg(msg);
    response.setData(data);
    return response;
  }
}
