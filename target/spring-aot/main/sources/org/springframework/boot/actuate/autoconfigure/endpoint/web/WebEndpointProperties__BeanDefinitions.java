package org.springframework.boot.actuate.autoconfigure.endpoint.web;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link WebEndpointProperties}.
 */
public class WebEndpointProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'webEndpointProperties'.
   */
  public static BeanDefinition getWebEndpointPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebEndpointProperties.class);
    beanDefinition.setInstanceSupplier(WebEndpointProperties::new);
    return beanDefinition;
  }
}
