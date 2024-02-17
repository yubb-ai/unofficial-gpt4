package com.gpt4.copilot;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link copilotApplication}.
 */
public class copilotApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'copilotApplication'.
   */
  public static BeanDefinition getCopilotApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(copilotApplication.class);
    beanDefinition.setTargetType(copilotApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(copilotApplication.class);
    beanDefinition.setInstanceSupplier(copilotApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
