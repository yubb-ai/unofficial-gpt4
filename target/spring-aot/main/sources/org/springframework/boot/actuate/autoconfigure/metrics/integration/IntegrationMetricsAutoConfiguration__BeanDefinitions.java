package org.springframework.boot.actuate.autoconfigure.metrics.integration;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link IntegrationMetricsAutoConfiguration}.
 */
public class IntegrationMetricsAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'integrationMetricsAutoConfiguration'.
   */
  public static BeanDefinition getIntegrationMetricsAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(IntegrationMetricsAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(IntegrationMetricsAutoConfiguration::new);
    return beanDefinition;
  }
}
