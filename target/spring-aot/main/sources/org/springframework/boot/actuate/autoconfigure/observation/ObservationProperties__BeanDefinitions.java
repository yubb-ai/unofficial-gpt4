package org.springframework.boot.actuate.autoconfigure.observation;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ObservationProperties}.
 */
public class ObservationProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'observationProperties'.
   */
  public static BeanDefinition getObservationPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationProperties.class);
    beanDefinition.setInstanceSupplier(ObservationProperties::new);
    return beanDefinition;
  }
}
