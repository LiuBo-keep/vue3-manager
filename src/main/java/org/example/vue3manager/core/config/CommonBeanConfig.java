package org.example.vue3manager.core.config;

import org.example.vue3manager.core.snowflake.SnowflakeIdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeanConfig {

  @Bean
  public SnowflakeIdGenerator snowflakeIdGenerator() {
    // 可以自定义 datacenterId 和 machineId，最大值都是 31 433165377208578048
    long datacenterId = 1L;
    long machineId = 31L;
    return new SnowflakeIdGenerator(datacenterId, machineId);
  }
}
