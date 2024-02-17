package org.springframework.boot.actuate.autoconfigure.metrics.export.prometheus;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link PrometheusProperties}.
 */
public class PrometheusProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'prometheusProperties'.
   */
  public static BeanDefinition getPrometheusPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PrometheusProperties.class);
    beanDefinition.setInstanceSupplier(PrometheusProperties::new);
    return beanDefinition;
  }
}
