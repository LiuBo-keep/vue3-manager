package org.example.vue3manager.dao.model;

import java.time.LocalDateTime;
import lombok.Data;
import org.apache.ibatis.type.Alias;

/**
 * 安全密钥类，用于存储密钥对信息
 * 包括密钥的唯一标识符、公钥、私钥以及创建和更新的元数据
 *
 * @author aidan.liu
 */
@Data
@Alias("SecurityKey")
public class SecurityKey {

  /**
   * 密钥的唯一标识符
   */
  private String id;

  /**
   * 公钥，用于加密数据或验证数字签名
   */
  private String publicKey;

  /**
   * 私钥，与公钥配对，用于解密数据或生成数字签名
   */
  private String privateKey;

  /**
   * 访问token密钥
   */
  private String accessTokenKey;

  /**
   * 刷新token密钥
   */
  private String refreshTokenKey;

  /**
   * AES密钥
   */
  private String aesKey;

  /**
   * 创建时间，记录密钥对何时被创建
   */
  private LocalDateTime createTime;

  /**
   * 创建者，记录创建密钥对的用户信息
   */
  private String createBy;

  /**
   * 更新时间，记录密钥对最后一次修改的时间
   */
  private LocalDateTime updateTime;

  /**
   * 更新者，记录最后一次修改密钥对的用户信息
   */
  private String updateBy;
}

