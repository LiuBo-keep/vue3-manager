package org.example.vue3manager.core.appinitiate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import org.example.vue3manager.dao.SecurityKeyDao;
import org.example.vue3manager.dao.model.SecurityKey;
import org.example.vue3manager.utils.AesEncryptorUtils;
import org.example.vue3manager.utils.RSAKeyUtil;
import org.springframework.stereotype.Component;

@Component
public class SystemKeyInit implements SystemInit {

  private final SecurityKeyDao securityKeyDao;

  public SystemKeyInit(SecurityKeyDao securityKeyDao) {
    this.securityKeyDao = securityKeyDao;
  }

  @Override
  public void init() {

    SecurityKey securityKey = securityKeyDao.findKey();
    if (Objects.nonNull(securityKey)) {
      return;
    }

    RSAKeyUtil rsaKeyUtil = new RSAKeyUtil();
    String publicKey = rsaKeyUtil.getPublicKey();
    String privateKey = rsaKeyUtil.getPrivateKey();

    securityKey = new SecurityKey();
    securityKey.setId(UUID.randomUUID().toString());
    securityKey.setPublicKey(AesEncryptorUtils.encrypt(publicKey));
    securityKey.setPrivateKey(AesEncryptorUtils.encrypt(privateKey));
    securityKey.setCreateBy("System");
    securityKey.setCreateTime(LocalDateTime.now());

    securityKeyDao.insert(securityKey);
  }
}
