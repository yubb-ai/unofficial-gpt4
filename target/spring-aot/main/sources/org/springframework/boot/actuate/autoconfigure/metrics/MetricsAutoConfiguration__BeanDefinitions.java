package org.springframework.boot.actuate.autoconfigure.metrics;

import io.micrometer.core.instrument.Clock;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;

/**
 * Bean definitions for {@link MetricsAutoConfiguration}.
 */
public class MetricsAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'metricsAutoConfiguration'.
   */
  public static BeanDefinition getMetricsAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MetricsAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(MetricsAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'micrometerClock'.
   */
  private static BeanInstanceSupplier<Clock> getMicrometerClockInstanceSupplier() {
    return BeanInstanceSupplier.<Clock>forFactoryMethod(MetricsAutoConfiguration.class, "micrometerClock")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(MetricsAutoConfiguration.class).micrometerClock());
  }

  /**
   * Get the bean definition for 'micrometerClock'.
   */
  public static BeanDefinition getMicrometerClockBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Clock.class);
    beanDefinition.setInstanceSupplier(getMicrometerClockInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'meterRegistryPostProcessor'.
   */
  private static BeanInstanceSupplier<MeterRegistryPostProcessor> getMeterRegistryPostProcessorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MeterRegistryPostProcessor>forFactoryMethod(MetricsAutoConfiguration.class, "meterRegistryPostProcessor", ApplicationContext.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> MetricsAutoConfiguration.meterRegistryPostProcessor(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'meterRegistryPostProcessor'.
   */
  public static BeanDefinition getMeterRegistryPostProcessorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MetricsAutoConfiguration.class);
    beanDefinition.setTargetType(MeterRegistryPostProcessor.class);
    beanDefinition.setInstanceSupplier(getMeterRegistryPostProcessorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'propertiesMeterFilter'.
   */
  private static BeanInstanceSupplier<PropertiesMeterFilter> getPropertiesMeterFilterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<PropertiesMeterFilter>forFactoryMethod(MetricsAutoConfiguration.class, "propertiesMeterFilter", MetricsProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(MetricsAutoConfiguration.class).propertiesMeterFilter(args.get(0)));
  }

  /**
   * Get the bean definition for 'propertiesMeterFilter'.
   */
  public static BeanDefinition getPropertiesMeterFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PropertiesMeterFilter.class);
    beanDefinition.setInstanceSupplier(getPropertiesMeterFilterInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'meterRegistryCloser'.
   */
  private static BeanInstanceSupplier<MetricsAutoConfiguration.MeterRegistryCloser> getMeterRegistryCloserInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MetricsAutoConfiguration.MeterRegistryCloser>forFactoryMethod(MetricsAutoConfiguration.class, "meterRegistryCloser", ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(MetricsAutoConfiguration.class).meterRegistryCloser(args.get(0)));
  }

  /**
   * Get the bean definition for 'meterRegistryCloser'.
   */
  public static BeanDefinition getMeterRegistryCloserBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MetricsAutoConfiguration.MeterRegistryCloser.class);
    beanDefinition.setInstanceSupplier(getMeterRegistryCloserInstanceSupplier());
    return beanDefinition;
  }
}
