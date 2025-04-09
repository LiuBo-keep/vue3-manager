package org.example.vue3manager.core.auth.jwttoken;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.vue3manager.core.auth.constant.AuthFlag;
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

  private ObjectMapper objectMapper = new ObjectMapper();
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
    String accessTokenKey = securityKeyDao.findAccessTokenKey();
    return buildToken(subject, accessTokenKey, AuthFlag.ACCESS_TOKEN_EXPIRATION);
  }

  /**
   * 生成刷新令牌
   *
   * @param subject 令牌的主题，通常是用户的唯一标识
   * @return 生成的刷新令牌字符串
   */
  public String generateRefreshToken(String subject) {
    String refreshTokenKey = securityKeyDao.findRefreshTokenKey();
    return buildToken(subject, refreshTokenKey, AuthFlag.REFRESH_TOKEN_EXPIRATION);
  }

  /**
   * 构建 JWT 令牌
   *
   * @param subject           令牌的主题，通常是用户的唯一标识
   * @param expirationSeconds 令牌的过期时间，单位为秒
   * @return 构建的 JWT 令牌字符串
   */
  private String buildToken(String subject, String salt, long expirationSeconds) {
    Date now = new Date();
    Date expiration = new Date(now.getTime() + expirationSeconds * 1000);

    return Jwts.builder()
        .setSubject(subject)
        .setIssuer(ISSUER)
        .setIssuedAt(now)
        .setExpiration(expiration)
        .signWith(getPrivateKey(salt), SignatureAlgorithm.RS256)
        .compact();
  }


  /**
   * 验证 JWT 令牌
   *
   * @param token JWT 令牌字符串
   */
  public boolean validateToken(String token, String salt) {

    try {
      // 验证 token 和解析 claims
      Jwts.parser()
          .setSigningKey(getPublicKey(salt))
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
  private PrivateKey getPrivateKey(String salt) {
    String privateKey = securityKeyDao.findPrivateKey();
    AesEncryptorUtils aesEncryptor = new AesEncryptorUtils(Base64.getDecoder().decode(salt));
    String decrypt = aesEncryptor.decrypt(privateKey);
    return RSAKeyUtil.decodePrivateKey(decrypt);
  }

  /**
   * 获取公钥
   *
   * @return 解密后的公钥对象
   */
  private PublicKey getPublicKey(String salt) {
    String encryptedPublicKey = securityKeyDao.findPublicKey();
    AesEncryptorUtils aesEncryptor = new AesEncryptorUtils(Base64.getDecoder().decode(salt));
    String publicKeyStr = aesEncryptor.decrypt(encryptedPublicKey);
    return RSAKeyUtil.decodePublicKey(publicKeyStr);
  }


  public JwtToken parseToken(String token) {
    JwtToken jwtToken = new JwtToken();
    jwtToken.setToken(token);
    jwtToken.setSignature(parseTokenSignature(token));
    jwtToken.setHeader(parseTokenHeader(token));
    jwtToken.setPayload(parseTokenPayload(token));
    return jwtToken;
  }

  private Map<String, Object> parseTokenHeader(String token) {

    try {
      byte[] bytes = Base64.getUrlDecoder().decode(StringUtils.split(token, ".")[0]);
      String payload = new String(bytes, StandardCharsets.UTF_8);
      return objectMapper.readValue(payload, new TypeReference<Map<String, Object>>() {
      });
    } catch (JsonProcessingException e) {
      return new HashMap<>(0);
    }
  }

  private Map<String, Object> parseTokenPayload(String token) {

    try {
      byte[] bytes = Base64.getUrlDecoder().decode(StringUtils.split(token, ".")[1]);
      String payload = new String(bytes, StandardCharsets.UTF_8);
      return objectMapper.readValue(payload, new TypeReference<Map<String, Object>>() {
      });
    } catch (JsonProcessingException e) {
      return new HashMap<>(0);
    }
  }

  private String parseTokenSignature(String token) {
    return StringUtils.split(token, ".")[2];
  }
}
