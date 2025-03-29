package org.example.vue3manager.utils;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


/**
 * RSAKeyUtil 类提供了生成和编码/解码 RSA 密钥对的功能。
 * 它使用 2048 位的密钥长度，并提供了获取公钥和私钥的方法。
 *
 * @author aidan.liu
 */
public class RSAKeyUtil {
    /**
     * RSA 密钥的长度，单位为位（2048 位）
     */
    private static final int KEY_SIZE = 2048;

    /**
     * 生成的 RSA 密钥对
     */
    private final KeyPair keyPair;

    /**
     * RSAKeyUtil 构造函数，生成一个 RSA 密钥对并初始化 keyPair 字段
     */
    public RSAKeyUtil() {
        this.keyPair = generateKeyPair();
    }

    /**
     * 生成 RSA 密钥对
     *
     * @return 生成的 RSA 密钥对
     * @throws SecurityException 如果生成密钥对失败
     */
    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new SecurityException("Failed to generate RSA key pair", e);
        }
    }

    /**
     * 获取公钥并以 Base64 编码的形式返回
     *
     * @return Base64 编码的公钥字符串
     */
    public String getPublicKey() {
        return encodeKey(keyPair.getPublic());
    }

    /**
     * 获取私钥并以 Base64 编码的形式返回
     *
     * @return Base64 编码的私钥字符串
     */
    public String getPrivateKey() {
        return encodeKey(keyPair.getPrivate());
    }

    /**
     * 获取公钥对象
     *
     * @return 公钥对象
     */
    public PublicKey getPublicKeyObject() {
        return keyPair.getPublic();
    }

    /**
     * 获取私钥对象
     *
     * @return 私钥对象
     */
    public PrivateKey getPrivateKeyObject() {
        return keyPair.getPrivate();
    }

    /**
     * 将密钥编码为 Base64 字符串
     *
     * @param key 需要编码的密钥对象
     * @return Base64 编码的密钥字符串
     * @throws SecurityException 如果编码失败，例如密钥为空
     */
    private String encodeKey(Key key) {
        byte[] encoded = key.getEncoded();
        if (encoded == null || encoded.length == 0) {
            throw new SecurityException("Failed to encode key: key is empty");
        }
        return Base64.getEncoder().encodeToString(encoded);
    }

    /**
     * 从 Base64 编码的字符串中解码出公钥对象
     *
     * @param base64PublicKey Base64 编码的公钥字符串
     * @return 解码后的公钥对象
     * @throws SecurityException 如果解码失败
     */
    public static PublicKey decodePublicKey(String base64PublicKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(spec);
        } catch (Exception e) {
            throw new SecurityException("Failed to decode public key", e);
        }
    }

    /**
     * 从 Base64 编码的字符串中解码出私钥对象
     *
     * @param base64PrivateKey Base64 编码的私钥字符串
     * @return 解码后的私钥对象
     * @throws SecurityException 如果解码失败
     */
    public static PrivateKey decodePrivateKey(String base64PrivateKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64PrivateKey);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(spec);
        } catch (Exception e) {
            throw new SecurityException("Failed to decode private key", e);
        }
    }
}

