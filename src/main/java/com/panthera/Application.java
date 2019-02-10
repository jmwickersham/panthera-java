package com.panthera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.HandlerExceptionResolver;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing
@EnableSwagger2
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public ServletContextInitializer sentryServletContextInitializer() {
    return new io.sentry.spring.SentryServletContextInitializer();
  }

  @Bean
  public HandlerExceptionResolver sentryExceptionResolver() {
    return new io.sentry.spring.SentryExceptionResolver();
  }
}
