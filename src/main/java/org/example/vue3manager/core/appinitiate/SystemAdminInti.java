package org.example.vue3manager.core.appinitiate;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.vue3manager.dao.SecurityUserDao;
import org.example.vue3manager.dao.model.SecurityUser;
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
    SecurityUser securityUser = new SecurityUser();
    securityUser.setId(UUID.randomUUID().toString());
    securityUser.setName("admin");
    securityUser.setPassword("admin");
    securityUser.setCreateBy("system");
    securityUser.setCreateTime(LocalDateTime.now().toString());
    SecurityUser admin = securityUserDao.insert(securityUser);

    log.info("init admin user: {},passwords: {}", admin.getName(), admin.getPassword());
  }
}
