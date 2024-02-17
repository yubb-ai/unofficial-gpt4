package org.springframework.boot.actuate.autoconfigure.info;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link InfoContributorAutoConfiguration}.
 */
public class InfoContributorAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'infoContributorAutoConfiguration'.
   */
  public static BeanDefinition getInfoContributorAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InfoContributorAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(InfoContributorAutoConfiguration::new);
    return beanDefinition;
  }
}
