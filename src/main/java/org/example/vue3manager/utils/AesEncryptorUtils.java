package org.example.vue3manager.utils;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * AES加解密工具类
 *
 * @author aidan.liu
 */
@Slf4j
public class AesEncryptorUtils {
  // 加密密钥，使用UUID以确保唯一性和安全性
  private static final String PASSWORD = "e5195386-ea3b-4c91-b735-a3324b76d861";
  // 密钥算法名称
  private static final String KEY_ALGORITHM = "AES";
  // 默认的加密算法名称，使用ECB模式和PKCS5Padding填充方式
  private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

  /**
   * AES 加密操作.
   *
   * @param content 待加密的内容
   * @return 加密后的字符串，如果加密失败返回null
   */
  public static String encrypt(String content) {

    if (StringUtils.isBlank(content)) {
      return null;
    }
    try {
      // 创建密码器
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      byte[] byteContent = content.getBytes(UTF_8);
      // 初始化为加密模式的密码器
      cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
      // 加密
      byte[] result = cipher.doFinal(byteContent);
      return Base64.getEncoder().encodeToString(result);
    } catch (Exception ex) {
      log.error("encrypt error", ex);
    }

    return null;
  }

  /**
   * AES 解密操作.
   *
   * @param content 待解密的内容
   * @return 解密后的字符串，如果解密失败返回null
   */
  public static String decrypt(String content) {

    if (StringUtils.isBlank(content)) {
      return null;
    }
    try {
      // 实例化
      Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
      // 使用密钥初始化，设置为解密模式
      cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
      // 执行操作
      byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

      return new String(result, UTF_8);
    } catch (Exception ex) {
      log.error("decrypt error", ex);
    }
    return null;
  }

  /**
   * 生成加密秘钥.
   *
   * @return 生成的密钥，如果生成失败返回null
   */
  private static SecretKeySpec getSecretKey() {

    // 返回生成指定算法密钥生成器的 KeyGenerator 对象
    KeyGenerator kg;
    try {
      kg = KeyGenerator.getInstance(KEY_ALGORITHM);
      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
      random.setSeed(PASSWORD.getBytes(UTF_8));
      // AES 要求密钥长度为 128
      final int aesKeyLength = 128;
      kg.init(aesKeyLength, random);
      // 生成一个密钥
      SecretKey secretKey = kg.generateKey();
      // 转换为AES专用密钥
      return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
    } catch (NoSuchAlgorithmException ex) {
      log.error("getSecretKey error", ex);
    }
    return null;
  }
}
