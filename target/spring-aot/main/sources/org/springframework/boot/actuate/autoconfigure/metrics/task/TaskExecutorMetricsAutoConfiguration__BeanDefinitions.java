package org.springframework.boot.actuate.autoconfigure.metrics.task;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.LazyInitializationExcludeFilter;

/**
 * Bean definitions for {@link TaskExecutorMetricsAutoConfiguration}.
 */
public class TaskExecutorMetricsAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'taskExecutorMetricsAutoConfiguration'.
   */
  public static BeanDefinition getTaskExecutorMetricsAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskExecutorMetricsAutoConfiguration.class);
    InstanceSupplier<TaskExecutorMetricsAutoConfiguration> instanceSupplier = InstanceSupplier.using(TaskExecutorMetricsAutoConfiguration::new);
    instanceSupplier = instanceSupplier.andThen(TaskExecutorMetricsAutoConfiguration__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }

  /**
   * Get the bean definition for 'eagerTaskExecutorMetrics'.
   */
  public static BeanDefinition getEagerTaskExecutorMetricsBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskExecutorMetricsAutoConfiguration.class);
    beanDefinition.setTargetType(LazyInitializationExcludeFilter.class);
    beanDefinition.setInstanceSupplier(BeanInstanceSupplier.<LazyInitializationExcludeFilter>forFactoryMethod(TaskExecutorMetricsAutoConfiguration.class, "eagerTaskExecutorMetrics").withGenerator((registeredBean) -> TaskExecutorMetricsAutoConfiguration.eagerTaskExecutorMetrics()));
    return beanDefinition;
  }
}
