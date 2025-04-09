package org.example.vue3manager.core.oss.platform;

import org.example.vue3manager.core.oss.config.OssPlatformType;

/**
 * OssPlatformAdapter接口定义了oss平台适配器的标准行为
 * 它提供了一种方式来检查适配器是否支持特定类型的oss平台
 *
 * @author aidan.liu
 */
public interface OssPlatformAdapter {

  /**
   * 检查适配器是否支持指定类型的oss平台
   *
   * @param ossType OssPlatformType枚举值，代表不同类型的oss平台
   * @return 如果适配器支持指定类型的oss平台则返回true，否则返回false
   */
  Boolean isSupportOssType(OssPlatformType ossType);
}
