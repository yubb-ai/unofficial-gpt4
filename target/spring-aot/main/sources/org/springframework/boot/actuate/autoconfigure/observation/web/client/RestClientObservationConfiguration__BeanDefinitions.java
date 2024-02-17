package org.springframework.boot.actuate.autoconfigure.observation.web.client;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.boot.web.client.RestClientCustomizer;

/**
 * Bean definitions for {@link RestClientObservationConfiguration}.
 */
public class RestClientObservationConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'restClientObservationConfiguration'.
   */
  public static BeanDefinition getRestClientObservationConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RestClientObservationConfiguration.class);
    beanDefinition.setInstanceSupplier(RestClientObservationConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'observationRestClientCustomizer'.
   */
  private static BeanInstanceSupplier<RestClientCustomizer> getObservationRestClientCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RestClientCustomizer>forFactoryMethod(RestClientObservationConfiguration.class, "observationRestClientCustomizer", ObservationRegistry.class, ObjectProvider.class, ObservationProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(RestClientObservationConfiguration.class).observationRestClientCustomizer(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'observationRestClientCustomizer'.
   */
  public static BeanDefinition getObservationRestClientCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RestClientCustomizer.class);
    beanDefinition.setInstanceSupplier(getObservationRestClientCustomizerInstanceSupplier());
    return beanDefinition;
  }
}
