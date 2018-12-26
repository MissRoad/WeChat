package com.bing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication //识它是spring Boot 应用
@EnableScheduling //开启定时任务
@MapperScan("com.bing.mapper*")
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
