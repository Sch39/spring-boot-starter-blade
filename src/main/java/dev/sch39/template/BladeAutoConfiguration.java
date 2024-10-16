package dev.sch39.template;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

@Configuration
@ConditionalOnWebApplication
public class BladeAutoConfiguration {
  @Bean
  @ConditionalOnMissingBean(ViewResolver.class)
  public ViewResolver bladeAutoConfiguration() {
    return new BladeViewResolver();
  }

}
