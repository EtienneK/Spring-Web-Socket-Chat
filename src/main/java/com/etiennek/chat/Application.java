package com.etiennek.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.etiennek")
public class Application {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }
}
