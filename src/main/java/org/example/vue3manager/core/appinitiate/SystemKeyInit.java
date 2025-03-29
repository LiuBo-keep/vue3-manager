package org.example.vue3manager.core.appinitiate;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.example.vue3manager.dao.SecurityKeyDao;
import org.example.vue3manager.dao.model.SecurityKey;
import org.example.vue3manager.utils.AesEncryptorUtils;
import org.example.vue3manager.utils.RSAKeyUtil;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SystemKeyInit implements SystemInit {

    private final SecurityKeyDao securityKeyDao;

    public SystemKeyInit(SecurityKeyDao securityKeyDao) {
        this.securityKeyDao = securityKeyDao;
    }

    @Override
    public void init() {
        try {
            SecurityKey securityKey = securityKeyDao.findKey();
            if (Objects.nonNull(securityKey)) {
                return;
            }

            byte[] bytes = AesEncryptorUtils.generateSecureAesKey();
            AesEncryptorUtils aesEncryptorUtils = new AesEncryptorUtils(bytes);


            RSAKeyUtil rsaKeyUtil = new RSAKeyUtil();
            String publicKey = rsaKeyUtil.getPublicKey();
            String privateKey = rsaKeyUtil.getPrivateKey();

            securityKey = new SecurityKey();
            securityKey.setId(UUID.randomUUID().toString());
            securityKey.setAesKey(Base64.getEncoder().encodeToString(bytes));
            securityKey.setPublicKey(aesEncryptorUtils.encrypt(publicKey));
            securityKey.setPrivateKey(aesEncryptorUtils.encrypt(privateKey));
            securityKey.setCreateBy("System");
            securityKey.setCreateTime(LocalDateTime.now());

            securityKeyDao.insert(securityKey);
        } catch (Exception e) {
            log.error("SystemKeyInit error", e);
        }
    }
}
