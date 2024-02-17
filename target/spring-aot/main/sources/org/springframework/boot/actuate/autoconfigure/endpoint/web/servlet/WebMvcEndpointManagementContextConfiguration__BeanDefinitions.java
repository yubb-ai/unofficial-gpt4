package org.springframework.boot.actuate.autoconfigure.endpoint.web.servlet;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.endpoint.jackson.EndpointObjectMapper;
import org.springframework.boot.actuate.endpoint.web.EndpointMediaTypes;
import org.springframework.boot.actuate.endpoint.web.WebEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.ControllerEndpointHandlerMapping;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.core.env.Environment;

/**
 * Bean definitions for {@link WebMvcEndpointManagementContextConfiguration}.
 */
public class WebMvcEndpointManagementContextConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'webMvcEndpointManagementContextConfiguration'.
   */
  public static BeanDefinition getWebMvcEndpointManagementContextConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebMvcEndpointManagementContextConfiguration.class);
    beanDefinition.setInstanceSupplier(WebMvcEndpointManagementContextConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'webEndpointServletHandlerMapping'.
   */
  private static BeanInstanceSupplier<WebMvcEndpointHandlerMapping> getWebEndpointServletHandlerMappingInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<WebMvcEndpointHandlerMapping>forFactoryMethod(WebMvcEndpointManagementContextConfiguration.class, "webEndpointServletHandlerMapping", WebEndpointsSupplier.class, ServletEndpointsSupplier.class, ControllerEndpointsSupplier.class, EndpointMediaTypes.class, CorsEndpointProperties.class, WebEndpointProperties.class, Environment.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcEndpointManagementContextConfiguration.class).webEndpointServletHandlerMapping(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6)));
  }

  /**
   * Get the bean definition for 'webEndpointServletHandlerMapping'.
   */
  public static BeanDefinition getWebEndpointServletHandlerMappingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebMvcEndpointHandlerMapping.class);
    beanDefinition.setInstanceSupplier(getWebEndpointServletHandlerMappingInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'controllerEndpointHandlerMapping'.
   */
  private static BeanInstanceSupplier<ControllerEndpointHandlerMapping> getControllerEndpointHandlerMappingInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ControllerEndpointHandlerMapping>forFactoryMethod(WebMvcEndpointManagementContextConfiguration.class, "controllerEndpointHandlerMapping", ControllerEndpointsSupplier.class, CorsEndpointProperties.class, WebEndpointProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcEndpointManagementContextConfiguration.class).controllerEndpointHandlerMapping(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'controllerEndpointHandlerMapping'.
   */
  public static BeanDefinition getControllerEndpointHandlerMappingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ControllerEndpointHandlerMapping.class);
    beanDefinition.setInstanceSupplier(getControllerEndpointHandlerMappingInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'endpointObjectMapperWebMvcConfigurer'.
   */
  private static BeanInstanceSupplier<WebMvcEndpointManagementContextConfiguration.EndpointObjectMapperWebMvcConfigurer> getEndpointObjectMapperWebMvcConfigurerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<WebMvcEndpointManagementContextConfiguration.EndpointObjectMapperWebMvcConfigurer>forFactoryMethod(WebMvcEndpointManagementContextConfiguration.class, "endpointObjectMapperWebMvcConfigurer", EndpointObjectMapper.class)
            .withGenerator((registeredBean, args) -> WebMvcEndpointManagementContextConfiguration.endpointObjectMapperWebMvcConfigurer(args.get(0)));
  }

  /**
   * Get the bean definition for 'endpointObjectMapperWebMvcConfigurer'.
   */
  public static BeanDefinition getEndpointObjectMapperWebMvcConfigurerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebMvcEndpointManagementContextConfiguration.class);
    beanDefinition.setTargetType(WebMvcEndpointManagementContextConfiguration.EndpointObjectMapperWebMvcConfigurer.class);
    beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
    beanDefinition.setInstanceSupplier(getEndpointObjectMapperWebMvcConfigurerInstanceSupplier());
    return beanDefinition;
  }
}
