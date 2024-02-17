package org.springframework.boot.actuate.autoconfigure.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;

/**
 * Bean definitions for {@link MetricsEndpointAutoConfiguration}.
 */
public class MetricsEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'metricsEndpointAutoConfiguration'.
   */
  public static BeanDefinition getMetricsEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MetricsEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(MetricsEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'metricsEndpoint'.
   */
  private static BeanInstanceSupplier<MetricsEndpoint> getMetricsEndpointInstanceSupplier() {
    return BeanInstanceSupplier.<MetricsEndpoint>forFactoryMethod(MetricsEndpointAutoConfiguration.class, "metricsEndpoint", MeterRegistry.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(MetricsEndpointAutoConfiguration.class).metricsEndpoint(args.get(0)));
  }

  /**
   * Get the bean definition for 'metricsEndpoint'.
   */
  public static BeanDefinition getMetricsEndpointBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MetricsEndpoint.class);
    beanDefinition.setInstanceSupplier(getMetricsEndpointInstanceSupplier());
    return beanDefinition;
  }
}
