package org.example.vue3manager.core.auth.jwttoken;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.example.vue3manager.dao.SecurityKeyDao;
import org.example.vue3manager.utils.AesEncryptorUtils;
import org.example.vue3manager.utils.RSAKeyUtil;
import org.springframework.stereotype.Component;

/**
 * JwtTokenManager 类负责生成和解析 JWT 令牌。
 * 它使用 RSA 算法来签名和验证令牌，并从数据库中获取必要的密钥。
 *
 * @author aidan.liu
 */
@Slf4j
@Component
public class JwtTokenManager {

    /**
     * 令牌的发行者，通常是应用的名称
     */
    private static final String ISSUER = "your-app-name";
    /**
     * 访问令牌的过期时间，单位为秒（1小时）
     */
    private static final long ACCESS_TOKEN_EXPIRATION = 3600;
    /**
     * 刷新令牌的过期时间，单位为秒（7天）
     */
    private static final long REFRESH_TOKEN_EXPIRATION = 86400 * 7;

    /**
     * 用于访问数据库中的安全密钥的 DAO
     */
    private final SecurityKeyDao securityKeyDao;


    /**
     * JwtTokenManager 构造函数
     *
     * @param securityKeyDao 用于访问数据库中的安全密钥的 DAO
     */
    public JwtTokenManager(SecurityKeyDao securityKeyDao) {
        this.securityKeyDao = securityKeyDao;
    }


    /**
     * 生成访问令牌
     *
     * @param subject 令牌的主题，通常是用户的唯一标识
     * @return 生成的访问令牌字符串
     */
    public String generateAccessToken(String subject) {
        return buildToken(subject, ACCESS_TOKEN_EXPIRATION);
    }

    /**
     * 生成刷新令牌
     *
     * @param subject 令牌的主题，通常是用户的唯一标识
     * @return 生成的刷新令牌字符串
     */
    public String generateRefreshToken(String subject) {
        return buildToken(subject, REFRESH_TOKEN_EXPIRATION);
    }

    /**
     * 构建 JWT 令牌
     *
     * @param subject           令牌的主题，通常是用户的唯一标识
     * @param expirationSeconds 令牌的过期时间，单位为秒
     * @return 构建的 JWT 令牌字符串
     */
    private String buildToken(String subject, long expirationSeconds) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationSeconds * 1000);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(ISSUER)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }


    /**
     * 验证 JWT 令牌
     *
     * @param token JWT 令牌字符串
     */
    public boolean validateToken(String token) {

        try {
            // 验证 token 和解析 claims
            Jwts.parser()
                    .setSigningKey(getPublicKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

        } catch (Exception e) {
            log.error("validateToken error", e);
            return false;
        }
        return true;
    }

    /**
     * 获取私钥
     *
     * @return 解密后的私钥对象
     */
    private PrivateKey getPrivateKey() {
        String privateKey = securityKeyDao.findPrivateKey();
        String aesKey = securityKeyDao.findAesKey();
        AesEncryptorUtils aesEncryptor = new AesEncryptorUtils(Base64.getDecoder().decode(aesKey));
        String decrypt = aesEncryptor.decrypt(privateKey);
        return RSAKeyUtil.decodePrivateKey(decrypt);
    }

    /**
     * 获取公钥
     *
     * @return 解密后的公钥对象
     */
    private PublicKey getPublicKey() {
        String encryptedPublicKey = securityKeyDao.findPublicKey();
        String aesKey = securityKeyDao.findAesKey();
        AesEncryptorUtils aesEncryptor = new AesEncryptorUtils(Base64.getDecoder().decode(aesKey));
        String publicKeyStr = aesEncryptor.decrypt(encryptedPublicKey);
        return RSAKeyUtil.decodePublicKey(publicKeyStr);
    }

    /**
     * 将 LocalDateTime 转换为 Date
     *
     * @param localDateTime 需要转换的 LocalDateTime 对象
     * @return 转换后的 Date 对象
     */
    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
