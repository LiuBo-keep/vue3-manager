package org.example.vue3manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.example.vue3manager.dao.mapper")
@SpringBootApplication
public class Vue3ManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(Vue3ManagerApplication.class, args);
  }

}
