package org.springframework.boot.actuate.autoconfigure.observation.web.client;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.boot.actuate.metrics.web.client.ObservationRestTemplateCustomizer;

/**
 * Bean definitions for {@link RestTemplateObservationConfiguration}.
 */
public class RestTemplateObservationConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'restTemplateObservationConfiguration'.
   */
  public static BeanDefinition getRestTemplateObservationConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RestTemplateObservationConfiguration.class);
    beanDefinition.setInstanceSupplier(RestTemplateObservationConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'observationRestTemplateCustomizer'.
   */
  private static BeanInstanceSupplier<ObservationRestTemplateCustomizer> getObservationRestTemplateCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ObservationRestTemplateCustomizer>forFactoryMethod(RestTemplateObservationConfiguration.class, "observationRestTemplateCustomizer", ObservationRegistry.class, ObjectProvider.class, ObservationProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(RestTemplateObservationConfiguration.class).observationRestTemplateCustomizer(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'observationRestTemplateCustomizer'.
   */
  public static BeanDefinition getObservationRestTemplateCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationRestTemplateCustomizer.class);
    beanDefinition.setInstanceSupplier(getObservationRestTemplateCustomizerInstanceSupplier());
    return beanDefinition;
  }
}
