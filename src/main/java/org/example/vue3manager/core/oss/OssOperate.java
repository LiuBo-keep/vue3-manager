package org.example.vue3manager.core.oss;

import org.example.vue3manager.common.exception.OssOperateException;

/**
 * OssOperate接口定义了对象存储服务的基本操作
 * 它提供了文件上传、下载和删除的方法
 *
 * @author aidan.liu
 */
public interface OssOperate {

  /**
   * 上传文件到对象存储服务
   *
   * @param data 文件数据，以字节数组形式提供
   * @return 文件的访问地址
   * @throws OssOperateException 如果上传过程中发生错误
   */
  String uploadFile(byte[] data) throws OssOperateException;

  /**
   * 在指定的基路径下上传文件到对象存储服务
   *
   * @param data     文件数据，以字节数组形式提供
   * @param basePath 文件上传的基路径
   * @return 文件的访问地址
   * @throws OssOperateException 如果上传过程中发生错误
   */
  String uploadFile(byte[] data, String basePath) throws OssOperateException;

  /**
   * 在指定的基路径下上传文件，并指定文件名
   *
   * @param data     文件数据，以字节数组形式提供
   * @param basePath 文件上传的基路径
   * @param fileName 文件名
   * @return 文件的访问地址
   * @throws OssOperateException 如果上传过程中发生错误
   */
  String uploadFile(byte[] data, String basePath, String fileName) throws OssOperateException;

  /**
   * 在指定的基路径下上传文件，指定文件名和文件类型
   *
   * @param data     文件数据，以字节数组形式提供
   * @param basePath 文件上传的基路径
   * @param fileName 文件名
   * @param fileType 文件类型，例如"jpg"、"png"
   * @return 文件的访问地址
   * @throws OssOperateException 如果上传过程中发生错误
   */
  String uploadFile(byte[] data, String basePath, String fileName, String fileType) throws OssOperateException;

  /**
   * 从对象存储服务下载文件
   *
   * @param filePath 文件的访问路径
   * @return 文件数据，以字节数组形式返回
   * @throws OssOperateException 如果下载过程中发生错误
   */
  byte[] downloadedFile(String filePath) throws OssOperateException;

  /**
   * 从对象存储服务删除文件
   *
   * @param filePath 文件的访问路径
   * @return 删除操作是否成功
   * @throws OssOperateException 如果删除过程中发生错误
   */
  boolean deleteFile(String filePath) throws OssOperateException;
}
