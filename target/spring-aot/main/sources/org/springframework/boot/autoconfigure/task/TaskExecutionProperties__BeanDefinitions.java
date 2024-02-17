package org.springframework.boot.autoconfigure.task;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TaskExecutionProperties}.
 */
public class TaskExecutionProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'taskExecutionProperties'.
   */
  public static BeanDefinition getTaskExecutionPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TaskExecutionProperties.class);
    beanDefinition.setInstanceSupplier(TaskExecutionProperties::new);
    return beanDefinition;
  }
}
