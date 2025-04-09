package org.example.vue3manager.core.oss.context;

import lombok.Data;

@Data
public class OssContext {

  /**
   * 数据.
   */
  private byte[] data;

  /**
   * 文件路径.
   */
  private String filePath;

  /**
   * 文件名称.
   */
  private String fileName;

  /**
   * 下载文件路径.
   */
  private String downloadFilePath;

  /**
   * 删除文件路径
   */
  private String deleteFilePath;
}
