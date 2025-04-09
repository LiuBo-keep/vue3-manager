package org.example.vue3manager.core.oss;

import org.example.vue3manager.common.exception.OssOperateException;
import org.springframework.stereotype.Component;

/**
 * EasyOss类实现了OssOperate接口，提供了一系列简化操作对象存储服务的方法
 * 它通过封装OssManager实例来执行实际的文件上传、下载和删除操作
 *
 * @author aidan.liu
 */
@Component
public final class EasyOss implements OssOperate {

  /**
   * OssManager实例，用于执行具体的文件操作
   */
  private final OssManager ossManager;

  /**
   * 构造方法，初始化EasyOss实例
   *
   * @param ossManager OssManager实例，用于执行文件操作
   */
  public EasyOss(OssManager ossManager) {
    this.ossManager = ossManager;
  }

  /**
   * 上传文件到对象存储服务，不指定基础路径和文件名
   *
   * @param data 文件数据，以字节数组形式提供
   * @return 文件上传后的访问地址
   * @throws OssOperateException 如果文件上传过程中发生错误
   */
  @Override
  public String uploadFile(byte[] data) throws OssOperateException {
    return ossManager.uploadFile(data);
  }

  /**
   * 上传文件到对象存储服务，指定基础路径
   *
   * @param data     文件数据，以字节数组形式提供
   * @param basePath 文件的基础路径
   * @return 文件上传后的访问地址
   * @throws OssOperateException 如果文件上传过程中发生错误
   */
  @Override
  public String uploadFile(byte[] data, String basePath) throws OssOperateException {
    return ossManager.uploadFile(data, basePath);
  }

  /**
   * 上传文件到对象存储服务，指定基础路径和文件名
   *
   * @param data     文件数据，以字节数组形式提供
   * @param basePath 文件的基础路径
   * @param fileName 文件名
   * @return 文件上传后的访问地址
   * @throws OssOperateException 如果文件上传过程中发生错误
   */
  @Override
  public String uploadFile(byte[] data, String basePath, String fileName) throws OssOperateException {
    return ossManager.uploadFile(data, basePath, fileName);
  }

  /**
   * 上传文件到对象存储服务，指定基础路径、文件名和文件类型
   *
   * @param data     文件数据，以字节数组形式提供
   * @param basePath 文件的基础路径
   * @param fileName 文件名
   * @param fileType 文件类型
   * @return 文件上传后的访问地址
   * @throws OssOperateException 如果文件上传过程中发生错误
   */
  @Override
  public String uploadFile(byte[] data, String basePath, String fileName, String fileType) throws OssOperateException {
    return ossManager.uploadFile(data, basePath, fileName, fileType);
  }

  /**
   * 从对象存储服务下载文件
   *
   * @param filePath 文件的访问路径
   * @return 下载的文件数据，以字节数组形式返回
   * @throws OssOperateException 如果文件下载过程中发生错误
   */
  @Override
  public byte[] downloadedFile(String filePath) throws OssOperateException {
    return ossManager.downloadedFile(filePath);
  }

  /**
   * 从对象存储服务删除文件
   *
   * @param filePath 文件的访问路径
   * @return 文件是否删除成功
   * @throws OssOperateException 如果文件删除过程中发生错误
   */
  @Override
  public boolean deleteFile(String filePath) throws OssOperateException {
    return ossManager.deleteFile(filePath);
  }
}
