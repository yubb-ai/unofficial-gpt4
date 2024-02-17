package org.springframework.boot.actuate.autoconfigure.scheduling;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ScheduledTasksObservabilityAutoConfiguration}.
 */
public class ScheduledTasksObservabilityAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'scheduledTasksObservabilityAutoConfiguration'.
   */
  public static BeanDefinition getScheduledTasksObservabilityAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ScheduledTasksObservabilityAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ScheduledTasksObservabilityAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'observabilitySchedulingConfigurer'.
   */
  private static BeanInstanceSupplier<ScheduledTasksObservabilityAutoConfiguration.ObservabilitySchedulingConfigurer> getObservabilitySchedulingConfigurerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ScheduledTasksObservabilityAutoConfiguration.ObservabilitySchedulingConfigurer>forFactoryMethod(ScheduledTasksObservabilityAutoConfiguration.class, "observabilitySchedulingConfigurer", ObservationRegistry.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(ScheduledTasksObservabilityAutoConfiguration.class).observabilitySchedulingConfigurer(args.get(0)));
  }

  /**
   * Get the bean definition for 'observabilitySchedulingConfigurer'.
   */
  public static BeanDefinition getObservabilitySchedulingConfigurerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ScheduledTasksObservabilityAutoConfiguration.ObservabilitySchedulingConfigurer.class);
    beanDefinition.setInstanceSupplier(getObservabilitySchedulingConfigurerInstanceSupplier());
    return beanDefinition;
  }
}
