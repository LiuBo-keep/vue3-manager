package org.example.vue3manager.core.appinitiate;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.example.vue3manager.common.exception.OssOperateException;
import org.example.vue3manager.core.oss.EasyOss;
import org.example.vue3manager.core.snowflake.SnowflakeIdGenerator;
import org.example.vue3manager.dao.security.SecurityUserDao;
import org.example.vue3manager.dao.security.model.SecurityUser;
import org.example.vue3manager.service.security.constant.UserStatus;
import org.example.vue3manager.service.security.constant.UserType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SystemAdminInti implements SystemInit {

  private static byte[] aidanAvatar;
  private final EasyOss easyOss;
  private final SecurityUserDao securityUserDao;
  private final SnowflakeIdGenerator snowflakeIdGenerator;

  public SystemAdminInti(EasyOss easyOss, SecurityUserDao securityUserDao, SnowflakeIdGenerator snowflakeIdGenerator) {
    this.easyOss = easyOss;
    this.securityUserDao = securityUserDao;
    this.snowflakeIdGenerator = snowflakeIdGenerator;
  }

  static {
    try (InputStream inputStream = SystemAdminInti.class.getClassLoader()
        .getResourceAsStream("static/admin-avatar.png")) {
      if (inputStream == null) {
        throw new IOException("Resource not found: static/admin-avatar.png");
      }
      aidanAvatar = inputStream.readAllBytes();
    } catch (IOException e) {
      log.error("Failed to load avatar from resource: {}", e.getMessage());
    }
  }

  @Override
  public void init() {
    String ADMIN_NAME = "admin";
    String ADMIN_PASSWORD = "admin";

    SecurityUser securityUser = securityUserDao.findByName(ADMIN_NAME);
    if (Objects.nonNull(securityUser)) {
      return;
    }
    securityUser = new SecurityUser();
    securityUser.setId(snowflakeIdGenerator.nextIdStr());
    securityUser.setName(ADMIN_NAME);
    securityUser.setPassword(ADMIN_PASSWORD);
    securityUser.setPasswordExpirationTime(LocalDateTime.now().plusYears(100));
    securityUser.setTemporaryPassword(false);
    securityUser.setUserType(UserType.ADMINISTRATOR);
    securityUser.setStatus(UserStatus.ENABLED);
    securityUser.setCreateBy("system");
    securityUser.setCreateTime(LocalDateTime.now());

    if (null != aidanAvatar && aidanAvatar.length > 0) {
      try {
        String path = easyOss.uploadFile(aidanAvatar);
        securityUser.setAvatarPath(path);
      } catch (OssOperateException e) {
        log.info("init admin avatar error {}", e.getMessage());
      }
    }

    SecurityUser admin = securityUserDao.insert(securityUser);

    log.info("init admin user: {},passwords: {}", admin.getName(), admin.getPassword());
  }
}
