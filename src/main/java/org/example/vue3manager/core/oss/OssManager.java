package org.example.vue3manager.core.oss;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.example.vue3manager.common.exception.OssOperateException;
import org.example.vue3manager.core.oss.config.OssConfig;
import org.example.vue3manager.core.oss.platform.AbstractOssPlatform;
import org.example.vue3manager.core.oss.utils.FileUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class OssManager implements OssOperate, ApplicationContextAware {

  private OssConfig ossConfig;
  private Map<String, AbstractOssPlatform> ossPlatform;


  @Override
  public String uploadFile(byte[] data) throws OssOperateException {
    return uploadFile(data, null, null, null);
  }

  @Override
  public String uploadFile(byte[] data, String basePath) throws OssOperateException {
    return uploadFile(data, basePath, null, null);
  }

  @Override
  public String uploadFile(byte[] data, String basePath, String fileName) throws OssOperateException {
    return uploadFile(data, basePath, fileName, null);
  }

  @Override
  public String uploadFile(byte[] data, String basePath, String fileName, String fileType) throws OssOperateException {
    String path = "";
    if (Boolean.FALSE.equals(ossConfig.isEnable())) {
      throw new OssOperateException("Oss is not enable");
    }

    if (StringUtils.isBlank(fileType)) {
      if (StringUtils.isNoneBlank(fileName)) {
        fileType = FileUtils.getFileTypeFromFileName(fileName);
        if ("unknown".equalsIgnoreCase(fileType)) {
          fileType = FileUtils.getFileTypeFromFileName(data);
        }
      }
    }

    for (AbstractOssPlatform ossPlatform : ossPlatform.values()) {
      if (ossPlatform.isSupportOssType(ossConfig.getPlatformType())) {
        path = ossPlatform.uploadFile(data, basePath, fileName, fileType);
        break;
      }
    }
    return path;
  }

  @Override
  public byte[] downloadedFile(String filePath) throws OssOperateException {
    byte[] data = null;
    if (Boolean.FALSE.equals(ossConfig.isEnable())) {
      throw new OssOperateException("Oss is not enable");
    }
    for (AbstractOssPlatform ossPlatform : ossPlatform.values()) {
      if (ossPlatform.isSupportOssType(ossConfig.getPlatformType())) {
        data = ossPlatform.downloadedFile(filePath);
        break;
      }
    }
    return data;
  }

  @Override
  public boolean deleteFile(String filePath) throws OssOperateException {
    boolean result = false;
    if (Boolean.FALSE.equals(ossConfig.isEnable())) {
      throw new OssOperateException("Oss is not enable");
    }
    for (AbstractOssPlatform ossPlatform : ossPlatform.values()) {
      if (ossPlatform.isSupportOssType(ossConfig.getPlatformType())) {
        result = ossPlatform.deleteFile(filePath);
        break;
      }
    }
    return result;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.ossConfig = applicationContext.getBean(OssConfig.class);
    this.ossPlatform = applicationContext.getBeansOfType(AbstractOssPlatform.class);
  }
}
