package org.springframework.boot.actuate.autoconfigure.web.server;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.env.Environment;

/**
 * Bean definitions for {@link ManagementContextAutoConfiguration}.
 */
public class ManagementContextAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'managementContextAutoConfiguration'.
   */
  public static BeanDefinition getManagementContextAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ManagementContextAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ManagementContextAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link ManagementContextAutoConfiguration.SameManagementContextConfiguration}.
   */
  public static class SameManagementContextConfiguration {
    /**
     * Get the bean instance supplier for 'org.springframework.boot.actuate.autoconfigure.web.server.ManagementContextAutoConfiguration$SameManagementContextConfiguration'.
     */
    private static BeanInstanceSupplier<ManagementContextAutoConfiguration.SameManagementContextConfiguration> getSameManagementContextConfigurationInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<ManagementContextAutoConfiguration.SameManagementContextConfiguration>forConstructor(Environment.class)
              .withGenerator((registeredBean, args) -> new ManagementContextAutoConfiguration.SameManagementContextConfiguration(args.get(0)));
    }

    /**
     * Get the bean definition for 'sameManagementContextConfiguration'.
     */
    public static BeanDefinition getSameManagementContextConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ManagementContextAutoConfiguration.SameManagementContextConfiguration.class);
      beanDefinition.setInstanceSupplier(getSameManagementContextConfigurationInstanceSupplier());
      return beanDefinition;
    }

    /**
     * Bean definitions for {@link ManagementContextAutoConfiguration.SameManagementContextConfiguration.EnableSameManagementContextConfiguration}.
     */
    public static class EnableSameManagementContextConfiguration {
      /**
       * Get the bean definition for 'enableSameManagementContextConfiguration'.
       */
      public static BeanDefinition getEnableSameManagementContextConfigurationBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(ManagementContextAutoConfiguration.SameManagementContextConfiguration.EnableSameManagementContextConfiguration.class);
        beanDefinition.setInstanceSupplier(ManagementContextAutoConfiguration.SameManagementContextConfiguration.EnableSameManagementContextConfiguration::new);
        return beanDefinition;
      }
    }
  }
}
