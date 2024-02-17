package org.springframework.boot.actuate.autoconfigure.metrics.export.prometheus;

import io.micrometer.core.instrument.Clock;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.CollectorRegistry;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusScrapeEndpoint;

/**
 * Bean definitions for {@link PrometheusMetricsExportAutoConfiguration}.
 */
public class PrometheusMetricsExportAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'prometheusMetricsExportAutoConfiguration'.
   */
  public static BeanDefinition getPrometheusMetricsExportAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PrometheusMetricsExportAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(PrometheusMetricsExportAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'prometheusConfig'.
   */
  private static BeanInstanceSupplier<PrometheusConfig> getPrometheusConfigInstanceSupplier() {
    return BeanInstanceSupplier.<PrometheusConfig>forFactoryMethod(PrometheusMetricsExportAutoConfiguration.class, "prometheusConfig", PrometheusProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(PrometheusMetricsExportAutoConfiguration.class).prometheusConfig(args.get(0)));
  }

  /**
   * Get the bean definition for 'prometheusConfig'.
   */
  public static BeanDefinition getPrometheusConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PrometheusConfig.class);
    beanDefinition.setInstanceSupplier(getPrometheusConfigInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'prometheusMeterRegistry'.
   */
  private static BeanInstanceSupplier<PrometheusMeterRegistry> getPrometheusMeterRegistryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<PrometheusMeterRegistry>forFactoryMethod(PrometheusMetricsExportAutoConfiguration.class, "prometheusMeterRegistry", PrometheusConfig.class, CollectorRegistry.class, Clock.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(PrometheusMetricsExportAutoConfiguration.class).prometheusMeterRegistry(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'prometheusMeterRegistry'.
   */
  public static BeanDefinition getPrometheusMeterRegistryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PrometheusMeterRegistry.class);
    beanDefinition.setDestroyMethodNames("close");
    beanDefinition.setInstanceSupplier(getPrometheusMeterRegistryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'collectorRegistry'.
   */
  private static BeanInstanceSupplier<CollectorRegistry> getCollectorRegistryInstanceSupplier() {
    return BeanInstanceSupplier.<CollectorRegistry>forFactoryMethod(PrometheusMetricsExportAutoConfiguration.class, "collectorRegistry")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(PrometheusMetricsExportAutoConfiguration.class).collectorRegistry());
  }

  /**
   * Get the bean definition for 'collectorRegistry'.
   */
  public static BeanDefinition getCollectorRegistryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CollectorRegistry.class);
    beanDefinition.setInstanceSupplier(getCollectorRegistryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link PrometheusMetricsExportAutoConfiguration.PrometheusScrapeEndpointConfiguration}.
   */
  public static class PrometheusScrapeEndpointConfiguration {
    /**
     * Get the bean definition for 'prometheusScrapeEndpointConfiguration'.
     */
    public static BeanDefinition getPrometheusScrapeEndpointConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(PrometheusMetricsExportAutoConfiguration.PrometheusScrapeEndpointConfiguration.class);
      beanDefinition.setInstanceSupplier(PrometheusMetricsExportAutoConfiguration.PrometheusScrapeEndpointConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'prometheusEndpoint'.
     */
    private static BeanInstanceSupplier<PrometheusScrapeEndpoint> getPrometheusEndpointInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<PrometheusScrapeEndpoint>forFactoryMethod(PrometheusMetricsExportAutoConfiguration.PrometheusScrapeEndpointConfiguration.class, "prometheusEndpoint", CollectorRegistry.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(PrometheusMetricsExportAutoConfiguration.PrometheusScrapeEndpointConfiguration.class).prometheusEndpoint(args.get(0)));
    }

    /**
     * Get the bean definition for 'prometheusEndpoint'.
     */
    public static BeanDefinition getPrometheusEndpointBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(PrometheusScrapeEndpoint.class);
      beanDefinition.setInstanceSupplier(getPrometheusEndpointInstanceSupplier());
      return beanDefinition;
    }
  }
}
