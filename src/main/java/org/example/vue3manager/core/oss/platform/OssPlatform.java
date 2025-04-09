package org.example.vue3manager.core.oss.platform;

import org.example.vue3manager.common.exception.OssOperateException;

/**
 * 对象存储平台接口，定义了文件上传、下载和删除的基本操作
 */
public interface OssPlatform {

  /**
   * 上传文件到指定的存储路径.
   *
   * @param data     文件的字节流数据.
   * @param basePath 文件的基础存储路径.
   * @param fileName 要上传的文件名.
   * @param fileType 要上传的文件类型
   * @return 存储后的文件路径.
   * @throws OssOperateException 如果文件上传过程中发生错误.
   */
  String uploadFile(byte[] data, String basePath, String fileName, String fileType) throws OssOperateException;

  /**
   * 从指定路径下载文件.
   *
   * @param filePath 要下载的文件路径.
   * @return 文件的字节流数据.
   * @throws OssOperateException 如果文件下载过程中发生错误.
   */
  byte[] downloadedFile(String filePath) throws OssOperateException;

  /**
   * 删除指定路径的文件.
   *
   * @param filePath 要删除的文件路径.
   * @return 如果文件删除成功返回true，否则返回false.
   * @throws OssOperateException 如果文件删除过程中发生错误.
   */
  boolean deleteFile(String filePath) throws OssOperateException;
}

