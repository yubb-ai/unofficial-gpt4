package org.springframework.context.event;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DefaultEventListenerFactory}.
 */
public class DefaultEventListenerFactory__BeanDefinitions {
  /**
   * Get the bean definition for 'internalEventListenerFactory'.
   */
  public static BeanDefinition getInternalEventListenerFactoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DefaultEventListenerFactory.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(DefaultEventListenerFactory::new);
    return beanDefinition;
  }
}
