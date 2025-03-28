package org.example.vue3manager.core.appinitiate;

import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitiateManager implements ApplicationRunner, ApplicationContextAware {
  private ApplicationContext applicationContext;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    Map<String, SystemInit> beansOfType = applicationContext.getBeansOfType(SystemInit.class);
    if (MapUtils.isNotEmpty(beansOfType)) {

    }
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
