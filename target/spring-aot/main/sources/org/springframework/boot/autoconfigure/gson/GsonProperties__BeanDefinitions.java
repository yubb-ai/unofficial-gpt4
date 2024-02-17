package org.springframework.boot.autoconfigure.gson;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GsonProperties}.
 */
public class GsonProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'gsonProperties'.
   */
  public static BeanDefinition getGsonPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GsonProperties.class);
    beanDefinition.setInstanceSupplier(GsonProperties::new);
    return beanDefinition;
  }
}
