package org.example.vue3manager.core.oss.platform;

import java.io.File;
import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.example.vue3manager.common.exception.OssOperateException;
import org.example.vue3manager.core.oss.context.OssContext;

public abstract class AbstractOssPlatform implements OssPlatform, OssPlatformAdapter {

  @Override
  public String uploadFile(byte[] data, String basePath, String fileName, String fileType) throws OssOperateException {
    if (data == null || data.length == 0) {
      throw new OssOperateException("上传文件失败，文件为空");
    }
    if (StringUtils.isBlank(basePath)) {
      basePath = generateBasePath();
    }

    if (StringUtils.isBlank(fileName)) {
      fileName = generateFileName();
    }

    if (StringUtils.isNoneBlank(fileType)) {
      if (fileType.startsWith(".")) {
        fileName = fileName + fileType;
      } else {
        fileName = fileType + "." + fileName;
      }
    }

    OssContext ossContext = new OssContext();
    if (!StringUtils.endsWith(basePath, File.separator)) {
      basePath = basePath + File.separator;
    }
    ossContext.setData(data);
    ossContext.setFilePath(basePath);
    ossContext.setFileName(fileName);
    return uploadFile(ossContext);
  }

  @Override
  public byte[] downloadedFile(String filePath) throws OssOperateException {
    if (filePath == null || filePath.isEmpty()) {
      throw new OssOperateException("下载文件失败，文件路径为空");
    }
    return new byte[0];
  }

  @Override
  public boolean deleteFile(String filePath) throws OssOperateException {
    if (filePath == null || filePath.isEmpty()) {
      throw new OssOperateException("删除文件失败，文件路径为空");
    }
    return false;
  }

  protected abstract String uploadFile(OssContext ossContext) throws OssOperateException;

  protected abstract byte[] downloadedFile(OssContext ossContext) throws OssOperateException;

  protected abstract boolean deleteFile(OssContext ossContext) throws OssOperateException;

  /**
   * 生成文件名
   *
   * <p>此方法用于生成一个唯一的文件名，以避免文件名冲突
   * 它通过使用UUID（通用唯一标识符）来实现这一点，确保每个文件名都是独一无二的
   *
   * @return 返回一个唯一的文件名作为字符串
   */
  private String generateFileName() {
    return UUID.randomUUID().toString();
  }

  /**
   * 生成基础路径字符串
   * 该方法根据当前日期生成一个按年、月、日分级的路径字符串
   * 主要用于组织文件存储结构，使得文件按照时间有序分布
   *
   * @return 返回格式为"年/月/日/"的路径字符串，例如"2023/10/12/"
   */
  private String generateBasePath() {
    LocalDateTime now = LocalDateTime.now();
    return now.getYear() + File.separator + now.getMonthValue() + File.separator + now.getDayOfMonth() + File.separator;
  }
}
