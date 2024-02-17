package org.springframework.boot.autoconfigure.http;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GsonHttpMessageConvertersConfiguration}.
 */
public class GsonHttpMessageConvertersConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'gsonHttpMessageConvertersConfiguration'.
   */
  public static BeanDefinition getGsonHttpMessageConvertersConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GsonHttpMessageConvertersConfiguration.class);
    beanDefinition.setInstanceSupplier(GsonHttpMessageConvertersConfiguration::new);
    return beanDefinition;
  }
}
