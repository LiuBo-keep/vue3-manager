package org.example.vue3manager.core.oss.platform.minio;

import lombok.extern.slf4j.Slf4j;
import org.example.vue3manager.common.exception.OssOperateException;
import org.example.vue3manager.core.oss.config.OssConfig;
import org.example.vue3manager.core.oss.config.OssPlatformType;
import org.example.vue3manager.core.oss.context.OssContext;
import org.example.vue3manager.core.oss.platform.AbstractOssPlatform;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MinioOssPlatform extends AbstractOssPlatform {

  private final OssConfig ossConfig;

  public MinioOssPlatform(OssConfig ossConfig) {
    this.ossConfig = ossConfig;
  }

  @Override
  protected String uploadFile(OssContext ossContext) throws OssOperateException {
    return "";
  }

  @Override
  protected byte[] downloadedFile(OssContext ossContext) throws OssOperateException {
    return new byte[0];
  }

  @Override
  protected boolean deleteFile(OssContext ossContext) throws OssOperateException {
    return false;
  }

  @Override
  public Boolean isSupportOssType(OssPlatformType ossType) {
    return OssPlatformType.MINIO.name().equalsIgnoreCase(ossType.name());
  }
}
