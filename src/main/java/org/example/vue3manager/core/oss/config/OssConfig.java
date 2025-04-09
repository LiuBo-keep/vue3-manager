package org.example.vue3manager.core.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * OssConfig类是Spring Boot中的一个配置类，用于配置对象存储服务（OSS）的相关参数
 * 它使用了Lombok的@Data注解来自动生成getter和setter方法，以及Spring的@Component和@ConfigurationProperties注解
 * 以便于Spring框架自动装配配置属性
 *
 * @author aidan.liu
 */
@Data
@Component
@ConfigurationProperties(prefix = "oss")
public class OssConfig {
  /**
   * 是否启用OSS服务的标志
   */
  private boolean enable;

  /**
   * OSS平台类型，决定了使用哪种OSS服务
   */
  private OssPlatformType platformType;

  /**
   * 本地磁盘配置，当选择本地磁盘作为OSS时使用
   */
  private LocalDisk localDisk;

  /**
   * MinIO配置，当选择MinIO作为OSS时使用
   */
  private Minio minio;

  /**
   * Minio类是OssConfig的嵌套类，用于配置MinIO对象存储服务的相关参数
   * 它同样使用了Lombok的@Data注解
   */
  @Data
  public static class Minio {
    /**
     * MinIO服务的终点URL
     */
    private String endpoint;

    /**
     * 访问MinIO服务的密钥
     */
    private String accessKey;

    /**
     * 访问MinIO服务的秘密密钥
     */
    private String secretKey;

    /**
     * 在MinIO中用于存储对象的桶名称
     */
    private String bucketName;
  }

  /**
   * LocalDisk类是OssConfig的嵌套类，用于配置本地磁盘作为OSS时的相关参数
   * 它同样使用了Lombok的@Data注解
   */
  @Data
  public static class LocalDisk {

    /**
     * 本地磁盘上用于存储文件的基础路径
     */
    private String basePath;
  }
}
