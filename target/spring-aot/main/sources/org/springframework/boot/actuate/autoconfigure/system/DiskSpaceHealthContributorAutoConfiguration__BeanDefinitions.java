package org.springframework.boot.actuate.autoconfigure.system;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;

/**
 * Bean definitions for {@link DiskSpaceHealthContributorAutoConfiguration}.
 */
public class DiskSpaceHealthContributorAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'diskSpaceHealthContributorAutoConfiguration'.
   */
  public static BeanDefinition getDiskSpaceHealthContributorAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DiskSpaceHealthContributorAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(DiskSpaceHealthContributorAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'diskSpaceHealthIndicator'.
   */
  private static BeanInstanceSupplier<DiskSpaceHealthIndicator> getDiskSpaceHealthIndicatorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<DiskSpaceHealthIndicator>forFactoryMethod(DiskSpaceHealthContributorAutoConfiguration.class, "diskSpaceHealthIndicator", DiskSpaceHealthIndicatorProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(DiskSpaceHealthContributorAutoConfiguration.class).diskSpaceHealthIndicator(args.get(0)));
  }

  /**
   * Get the bean definition for 'diskSpaceHealthIndicator'.
   */
  public static BeanDefinition getDiskSpaceHealthIndicatorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DiskSpaceHealthIndicator.class);
    beanDefinition.setInstanceSupplier(getDiskSpaceHealthIndicatorInstanceSupplier());
    return beanDefinition;
  }
}
