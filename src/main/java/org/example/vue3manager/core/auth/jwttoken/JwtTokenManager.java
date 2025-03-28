package org.example.vue3manager.core.auth.jwttoken;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import org.example.vue3manager.dao.SecurityKeyDao;
import org.example.vue3manager.utils.AesEncryptorUtils;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenManager {

  private final SecurityKeyDao securityKeyDao;

  public JwtTokenManager(SecurityKeyDao securityKeyDao) {
    this.securityKeyDao = securityKeyDao;
  }

  public String generateToken(String name) {

    LocalDateTime currentTime = LocalDateTime.now();
    Date from = Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
    String privateKey = securityKeyDao.findPrivateKey();
    privateKey = AesEncryptorUtils.decrypt(privateKey);
    byte[] decodedKey = Base64.getDecoder().decode(privateKey);
    Key key = new SecretKeySpec(decodedKey, HS512.getJcaName());
    return Jwts.builder()
        .subject(name)
        .issuer("")
        .issuedAt(from)
        .expiration(Date.from(currentTime.plusSeconds(3600).atZone(ZoneId.systemDefault()).toInstant()))
        .signWith(key, SignatureAlgorithm.RS256)
        .compact();
  }

  public JwtToken parseToken(String token) {
    JwtToken jwtToken = new JwtToken();
    String publicKey = securityKeyDao.findPublicKey();
    publicKey = AesEncryptorUtils.decrypt(publicKey);
    byte[] decodedKey = Base64.getDecoder().decode(publicKey);
    Key key = new SecretKeySpec(decodedKey, HS512.getJcaName());

    try {
      // 验证 token 和解析 claims
      Claims payload = Jwts.parser()
          .setSigningKey(key)
          .build()
          .parseSignedClaims(token)
          .getPayload();

    } catch (Exception e) {
      throw new RuntimeException("Invalid token signature");
    }

    return jwtToken;
  }
}
