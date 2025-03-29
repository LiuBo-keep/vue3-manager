package org.example.vue3manager.utils;


import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;


/**
 * AES 加密和解密工具类，使用 GCM 模式（Galois/Counter Mode）
 * 提供加密、解密以及生成安全 AES 密钥的功能
 *
 * @author aidan.liu
 */
@Slf4j
public class AesEncryptorUtils {
    private static final String AES_ALGORITHM = "AES";
    private static final String CIPHER_ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_IV_LENGTH = 12;
    private static final int GCM_TAG_LENGTH = 16;

    // 从安全配置获取，不要硬编码在代码中
    private final byte[] aesKey;

    /**
     * 构造函数，初始化 AES 密钥
     *
     * @param aesKey 256 位（32 字节）的 AES 密钥
     * @throws IllegalArgumentException 如果提供的密钥为空或长度不等于 32 字节
     */
    public AesEncryptorUtils(byte[] aesKey) {
        if (aesKey == null || aesKey.length != 32) {
            throw new IllegalArgumentException("AES key must be 256 bits (32 bytes)");
        }
        this.aesKey = aesKey.clone();
    }

    /**
     * 使用 AES 密钥加密字符串
     *
     * @param plaintext 要加密的明文字符串
     * @return 加密后的 Base64 编码的密文字符串
     * @throws SecurityException 如果加密失败
     */
    public String encrypt(String plaintext) {
        if (plaintext == null || plaintext.isEmpty()) {
            return null;
        }

        try {
            // 生成随机IV
            byte[] iv = new byte[GCM_IV_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            // 初始化GCM参数
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);

            // 初始化密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            // 加密数据
            byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

            // 组合IV和密文
            byte[] encrypted = new byte[iv.length + ciphertext.length];
            System.arraycopy(iv, 0, encrypted, 0, iv.length);
            System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new SecurityException("AES encryption failed", e);
        }
    }

    /**
     * 使用 AES 密钥解密 Base64 编码的密文字符串
     *
     * @param encryptedText Base64 编码的密文字符串
     * @return 解密后的明文字符串
     * @throws SecurityException 如果解密失败
     */
    public String decrypt(String encryptedText) {
        if (encryptedText == null || encryptedText.isEmpty()) {
            return null;
        }

        try {
            // 解码Base64
            byte[] decoded = Base64.getDecoder().decode(encryptedText);

            // 提取IV
            byte[] iv = new byte[GCM_IV_LENGTH];
            System.arraycopy(decoded, 0, iv, 0, iv.length);

            // 提取密文
            byte[] ciphertext = new byte[decoded.length - GCM_IV_LENGTH];
            System.arraycopy(decoded, iv.length, ciphertext, 0, ciphertext.length);

            // 初始化GCM参数
            GCMParameterSpec ivSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);

            // 初始化密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            // 解密数据
            byte[] plaintext = cipher.doFinal(ciphertext);

            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new SecurityException("AES decryption failed", e);
        }
    }

    /**
     * 生成一个安全的 256 位 AES 密钥
     *
     * @return 256 位 AES 密钥的字节数组
     * @throws SecurityException 如果生成密钥失败
     */
    public static byte[] generateSecureAesKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(AES_ALGORITHM);
            keyGen.init(256); // 使用256位AES密钥
            return keyGen.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("Failed to generate AES key", e);
        }
    }
}
