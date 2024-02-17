package org.springframework.boot.actuate.autoconfigure.metrics;

import io.micrometer.core.instrument.binder.logging.LogbackMetrics;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link LogbackMetricsAutoConfiguration}.
 */
public class LogbackMetricsAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'logbackMetricsAutoConfiguration'.
   */
  public static BeanDefinition getLogbackMetricsAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LogbackMetricsAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(LogbackMetricsAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'logbackMetrics'.
   */
  private static BeanInstanceSupplier<LogbackMetrics> getLogbackMetricsInstanceSupplier() {
    return BeanInstanceSupplier.<LogbackMetrics>forFactoryMethod(LogbackMetricsAutoConfiguration.class, "logbackMetrics")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(LogbackMetricsAutoConfiguration.class).logbackMetrics());
  }

  /**
   * Get the bean definition for 'logbackMetrics'.
   */
  public static BeanDefinition getLogbackMetricsBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LogbackMetrics.class);
    beanDefinition.setDestroyMethodNames("close");
    beanDefinition.setInstanceSupplier(getLogbackMetricsInstanceSupplier());
    return beanDefinition;
  }
}
