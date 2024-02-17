package com.gpt4.copilot.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link WebConfig}.
 */
public class WebConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'webConfig'.
   */
  public static BeanDefinition getWebConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebConfig.class);
    beanDefinition.setTargetType(WebConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(WebConfig.class);
    beanDefinition.setInstanceSupplier(WebConfig$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
