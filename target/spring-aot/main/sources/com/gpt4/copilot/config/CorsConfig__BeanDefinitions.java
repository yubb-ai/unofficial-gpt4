package com.gpt4.copilot.config;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;
import org.springframework.web.filter.CorsFilter;

/**
 * Bean definitions for {@link CorsConfig}.
 */
public class CorsConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'corsConfig'.
   */
  public static BeanDefinition getCorsConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CorsConfig.class);
    beanDefinition.setTargetType(CorsConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(CorsConfig.class);
    beanDefinition.setInstanceSupplier(CorsConfig$$SpringCGLIB$$0::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'corsFilter'.
   */
  private static BeanInstanceSupplier<CorsFilter> getCorsFilterInstanceSupplier() {
    return BeanInstanceSupplier.<CorsFilter>forFactoryMethod(CorsConfig.class, "corsFilter")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(CorsConfig.class).corsFilter());
  }

  /**
   * Get the bean definition for 'corsFilter'.
   */
  public static BeanDefinition getCorsFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CorsFilter.class);
    beanDefinition.setInstanceSupplier(getCorsFilterInstanceSupplier());
    return beanDefinition;
  }
}
