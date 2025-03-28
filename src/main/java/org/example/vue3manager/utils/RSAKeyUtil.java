package org.example.vue3manager.utils;

import java.security.*;
import java.util.Base64;

/**
 * RSAKeyUtil类用于生成和提供RSA密钥对（包括公钥和私钥）
 * 该类在构造时会生成一个RSA密钥对，并提供方法以字符串形式获取公钥和私钥
 */
public class RSAKeyUtil {
  // 保存生成的密钥对
  private final KeyPair keyPair;

  /**
   * 构造方法，用于生成RSA密钥对
   * 在实例化RSAKeyUtil对象时，会调用此构造方法生成一个2048位的RSA密钥对
   * 如果生成密钥对时出现NoSuchAlgorithmException异常，会将其转换为RuntimeException
   */
  public RSAKeyUtil() {
    try {
      // 创建KeyPairGenerator对象并初始化，用于生成RSA密钥对
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
      keyPairGenerator.initialize(2048);
      this.keyPair = keyPairGenerator.generateKeyPair();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("RSA KeyPair generation failed", e);
    }
  }

  /**
   * 获取公钥的字符串表示
   * 使用Base64编码将公钥的字节序列转换为字符串，以便于传输和存储
   *
   * @return 公钥的Base64编码字符串
   */
  public String getPublicKey() {
    return Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
  }

  /**
   * 获取私钥的字符串表示
   * 同样使用Base64编码，确保私钥可以安全地转换为字符串形式
   *
   * @return 私钥的Base64编码字符串
   */
  public String getPrivateKey() {
    return Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
  }
}

