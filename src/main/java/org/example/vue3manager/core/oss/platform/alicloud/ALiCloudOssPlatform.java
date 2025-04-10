package org.example.vue3manager.core.oss.platform.alicloud;

import org.example.vue3manager.common.exception.OssOperateException;
import org.example.vue3manager.core.oss.config.OssConfig;
import org.example.vue3manager.core.oss.config.OssPlatformType;
import org.example.vue3manager.core.oss.context.OssContext;
import org.example.vue3manager.core.oss.platform.AbstractOssPlatform;
import org.springframework.stereotype.Component;

@Component
public class ALiCloudOssPlatform extends AbstractOssPlatform {

  private final OssConfig ossConfig;

  public ALiCloudOssPlatform(OssConfig ossConfig) {
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
    return OssPlatformType.ALI_CLOUD_OSS.name().equalsIgnoreCase(ossType.name());
  }
}
