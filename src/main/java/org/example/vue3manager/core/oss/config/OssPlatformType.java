package org.example.vue3manager.core.oss.config;

/**
 * 定义了支持的对象存储平台类型。
 * 该枚举列举了不同的对象存储服务提供商或本地存储选项，包括：
 * 1. LOCAL_DISK: 表示数据存储在本地磁盘上。
 * 2. MINIO: 一种兼容 Amazon S3 接口的开源对象存储系统。
 * 3. ALI_CLOUD_OSS: 阿里云对象存储服务（OSS），提供大规模、安全且可靠的存储服务。
 * 4. TENCENT_COS: 腾讯云对象存储（COS），提供高性能、高可用的存储解决方案。
 * 5. HUAWEI_OBS: 华为云对象存储服务（OBS），提供经济高效、灵活扩展的存储服务。
 */
public enum OssPlatformType {
  // 数据存储在本地磁盘上
  LOCAL_DISK,

  // 使用 MINIO 对象存储系统
  MINIO,

  // 使用阿里云对象存储服务（OSS）
  ALI_CLOUD_OSS,

  // 使用腾讯云对象存储（COS）
  TENCENT_COS,

  // 使用华为云对象存储服务（OBS）
  HUAWEI_OBS,
}
