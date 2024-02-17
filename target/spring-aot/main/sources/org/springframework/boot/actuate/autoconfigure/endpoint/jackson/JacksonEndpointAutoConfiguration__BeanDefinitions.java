package org.springframework.boot.actuate.autoconfigure.endpoint.jackson;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.endpoint.jackson.EndpointObjectMapper;

/**
 * Bean definitions for {@link JacksonEndpointAutoConfiguration}.
 */
public class JacksonEndpointAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'jacksonEndpointAutoConfiguration'.
   */
  public static BeanDefinition getJacksonEndpointAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JacksonEndpointAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(JacksonEndpointAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'endpointObjectMapper'.
   */
  private static BeanInstanceSupplier<EndpointObjectMapper> getEndpointObjectMapperInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EndpointObjectMapper>forFactoryMethod(JacksonEndpointAutoConfiguration.class, "endpointObjectMapper")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(JacksonEndpointAutoConfiguration.class).endpointObjectMapper());
  }

  /**
   * Get the bean definition for 'endpointObjectMapper'.
   */
  public static BeanDefinition getEndpointObjectMapperBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EndpointObjectMapper.class);
    beanDefinition.setInstanceSupplier(getEndpointObjectMapperInstanceSupplier());
    return beanDefinition;
  }
}
