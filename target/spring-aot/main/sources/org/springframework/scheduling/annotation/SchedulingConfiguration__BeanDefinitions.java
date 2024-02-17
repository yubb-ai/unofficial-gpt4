package org.springframework.scheduling.annotation;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SchedulingConfiguration}.
 */
public class SchedulingConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'schedulingConfiguration'.
   */
  public static BeanDefinition getSchedulingConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SchedulingConfiguration.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(SchedulingConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'org.springframework.context.annotation.internalScheduledAnnotationProcessor'.
   */
  private static BeanInstanceSupplier<ScheduledAnnotationBeanPostProcessor> getInternalScheduledAnnotationProcessorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ScheduledAnnotationBeanPostProcessor>forFactoryMethod(SchedulingConfiguration.class, "scheduledAnnotationProcessor")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(SchedulingConfiguration.class).scheduledAnnotationProcessor());
  }

  /**
   * Get the bean definition for 'internalScheduledAnnotationProcessor'.
   */
  public static BeanDefinition getInternalScheduledAnnotationProcessorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ScheduledAnnotationBeanPostProcessor.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getInternalScheduledAnnotationProcessorInstanceSupplier());
    return beanDefinition;
  }
}
