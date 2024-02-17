package org.springframework.boot.actuate.autoconfigure.health;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link HealthEndpointAutoConfiguration}.
 */
public class HealthEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'healthEndpointAutoConfiguration'.
   */
  public static BeanDefinition getHealthEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HealthEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(HealthEndpointAutoConfiguration::new);
    return beanDefinition;
  }
}
