package org.springframework.boot.actuate.autoconfigure.availability;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AvailabilityHealthContributorAutoConfiguration}.
 */
public class AvailabilityHealthContributorAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'availabilityHealthContributorAutoConfiguration'.
   */
  public static BeanDefinition getAvailabilityHealthContributorAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AvailabilityHealthContributorAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(AvailabilityHealthContributorAutoConfiguration::new);
    return beanDefinition;
  }
}
