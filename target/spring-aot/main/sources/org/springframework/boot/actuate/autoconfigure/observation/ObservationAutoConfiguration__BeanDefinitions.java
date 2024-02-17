package org.springframework.boot.actuate.autoconfigure.observation;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ObservationAutoConfiguration}.
 */
public class ObservationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'observationAutoConfiguration'.
   */
  public static BeanDefinition getObservationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ObservationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'observationRegistryPostProcessor'.
   */
  private static BeanInstanceSupplier<ObservationRegistryPostProcessor> getObservationRegistryPostProcessorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ObservationRegistryPostProcessor>forFactoryMethod(ObservationAutoConfiguration.class, "observationRegistryPostProcessor", ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> ObservationAutoConfiguration.observationRegistryPostProcessor(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'observationRegistryPostProcessor'.
   */
  public static BeanDefinition getObservationRegistryPostProcessorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationAutoConfiguration.class);
    beanDefinition.setTargetType(ObservationRegistryPostProcessor.class);
    beanDefinition.setInstanceSupplier(getObservationRegistryPostProcessorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'observationRegistry'.
   */
  private static BeanInstanceSupplier<ObservationRegistry> getObservationRegistryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ObservationRegistry>forFactoryMethod(ObservationAutoConfiguration.class, "observationRegistry")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(ObservationAutoConfiguration.class).observationRegistry());
  }

  /**
   * Get the bean definition for 'observationRegistry'.
   */
  public static BeanDefinition getObservationRegistryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationRegistry.class);
    beanDefinition.setInstanceSupplier(getObservationRegistryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'propertiesObservationFilter'.
   */
  private static BeanInstanceSupplier<PropertiesObservationFilterPredicate> getPropertiesObservationFilterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<PropertiesObservationFilterPredicate>forFactoryMethod(ObservationAutoConfiguration.class, "propertiesObservationFilter", ObservationProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(ObservationAutoConfiguration.class).propertiesObservationFilter(args.get(0)));
  }

  /**
   * Get the bean definition for 'propertiesObservationFilter'.
   */
  public static BeanDefinition getPropertiesObservationFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PropertiesObservationFilterPredicate.class);
    beanDefinition.setInstanceSupplier(getPropertiesObservationFilterInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link ObservationAutoConfiguration.OnlyMetricsConfiguration}.
   */
  public static class OnlyMetricsConfiguration {
    /**
     * Get the bean definition for 'onlyMetricsConfiguration'.
     */
    public static BeanDefinition getOnlyMetricsConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationAutoConfiguration.OnlyMetricsConfiguration.class);
      beanDefinition.setInstanceSupplier(ObservationAutoConfiguration.OnlyMetricsConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'metricsObservationHandlerGrouping'.
     */
    private static BeanInstanceSupplier<ObservationHandlerGrouping> getMetricsObservationHandlerGroupingInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<ObservationHandlerGrouping>forFactoryMethod(ObservationAutoConfiguration.OnlyMetricsConfiguration.class, "metricsObservationHandlerGrouping")
              .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(ObservationAutoConfiguration.OnlyMetricsConfiguration.class).metricsObservationHandlerGrouping());
    }

    /**
     * Get the bean definition for 'metricsObservationHandlerGrouping'.
     */
    public static BeanDefinition getMetricsObservationHandlerGroupingBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationHandlerGrouping.class);
      beanDefinition.setInstanceSupplier(getMetricsObservationHandlerGroupingInstanceSupplier());
      return beanDefinition;
    }
  }

  /**
   * Bean definitions for {@link ObservationAutoConfiguration.MeterObservationHandlerConfiguration}.
   */
  public static class MeterObservationHandlerConfiguration {
    /**
     * Get the bean definition for 'meterObservationHandlerConfiguration'.
     */
    public static BeanDefinition getMeterObservationHandlerConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationAutoConfiguration.MeterObservationHandlerConfiguration.class);
      beanDefinition.setInstanceSupplier(ObservationAutoConfiguration.MeterObservationHandlerConfiguration::new);
      return beanDefinition;
    }

    /**
     * Bean definitions for {@link ObservationAutoConfiguration.MeterObservationHandlerConfiguration.OnlyMetricsMeterObservationHandlerConfiguration}.
     */
    public static class OnlyMetricsMeterObservationHandlerConfiguration {
      /**
       * Get the bean definition for 'onlyMetricsMeterObservationHandlerConfiguration'.
       */
      public static BeanDefinition getOnlyMetricsMeterObservationHandlerConfigurationBeanDefinition(
          ) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationAutoConfiguration.MeterObservationHandlerConfiguration.OnlyMetricsMeterObservationHandlerConfiguration.class);
        beanDefinition.setInstanceSupplier(ObservationAutoConfiguration.MeterObservationHandlerConfiguration.OnlyMetricsMeterObservationHandlerConfiguration::new);
        return beanDefinition;
      }

      /**
       * Get the bean instance supplier for 'defaultMeterObservationHandler'.
       */
      private static BeanInstanceSupplier<DefaultMeterObservationHandler> getDefaultMeterObservationHandlerInstanceSupplier(
          ) {
        return BeanInstanceSupplier.<DefaultMeterObservationHandler>forFactoryMethod(ObservationAutoConfiguration.MeterObservationHandlerConfiguration.OnlyMetricsMeterObservationHandlerConfiguration.class, "defaultMeterObservationHandler", MeterRegistry.class)
                .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(ObservationAutoConfiguration.MeterObservationHandlerConfiguration.OnlyMetricsMeterObservationHandlerConfiguration.class).defaultMeterObservationHandler(args.get(0)));
      }

      /**
       * Get the bean definition for 'defaultMeterObservationHandler'.
       */
      public static BeanDefinition getDefaultMeterObservationHandlerBeanDefinition() {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(DefaultMeterObservationHandler.class);
        beanDefinition.setInstanceSupplier(getDefaultMeterObservationHandlerInstanceSupplier());
        return beanDefinition;
      }
    }
  }
}
