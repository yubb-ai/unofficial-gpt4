package org.springframework.boot.actuate.autoconfigure.health;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link HealthEndpointProperties}.
 */
public class HealthEndpointProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'healthEndpointProperties'.
   */
  public static BeanDefinition getHealthEndpointPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HealthEndpointProperties.class);
    beanDefinition.setInstanceSupplier(HealthEndpointProperties::new);
    return beanDefinition;
  }
}
