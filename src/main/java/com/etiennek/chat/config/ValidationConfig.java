package com.etiennek.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidationConfig {
  @Bean
  public javax.validation.Validator localValidatorFactoryBean() {
    return new LocalValidatorFactoryBean();
  }
}
