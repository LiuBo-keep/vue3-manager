package org.example.vue3manager.core.appinitiate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.vue3manager.dao.security.SecurityUserDao;
import org.example.vue3manager.dao.security.model.SecurityUser;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SystemAdminInti implements SystemInit {

  private final SecurityUserDao securityUserDao;

  public SystemAdminInti(SecurityUserDao securityUserDao) {
    this.securityUserDao = securityUserDao;
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
    securityUser.setId(UUID.randomUUID().toString());
    securityUser.setName(ADMIN_NAME);
    securityUser.setPassword(ADMIN_PASSWORD);
    securityUser.setCreateBy("system");
    securityUser.setCreateTime(LocalDateTime.now());
    SecurityUser admin = securityUserDao.insert(securityUser);

    log.info("init admin user: {},passwords: {}", admin.getName(), admin.getPassword());
  }
}
