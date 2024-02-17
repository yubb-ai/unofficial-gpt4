package org.springframework.boot.autoconfigure.task;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.LazyInitializationExcludeFilter;

/**
 * Bean definitions for {@link TaskSchedulingAutoConfiguration}.
 */
public class TaskSchedulingAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'taskSchedulingAutoConfiguration'.
   */
  public static BeanDefinition getTaskSchedulingAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskSchedulingAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(TaskSchedulingAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean definition for 'scheduledBeanLazyInitializationExcludeFilter'.
   */
  public static BeanDefinition getScheduledBeanLazyInitializationExcludeFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskSchedulingAutoConfiguration.class);
    beanDefinition.setTargetType(LazyInitializationExcludeFilter.class);
    beanDefinition.setInstanceSupplier(BeanInstanceSupplier.<LazyInitializationExcludeFilter>forFactoryMethod(TaskSchedulingAutoConfiguration.class, "scheduledBeanLazyInitializationExcludeFilter").withGenerator((registeredBean) -> TaskSchedulingAutoConfiguration.scheduledBeanLazyInitializationExcludeFilter()));
    return beanDefinition;
  }
}
