package org.example.vue3manager.core.oss.config;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssBeanConfig {

  @Bean
  public Tika tika() {
    return new Tika();
  }
}
