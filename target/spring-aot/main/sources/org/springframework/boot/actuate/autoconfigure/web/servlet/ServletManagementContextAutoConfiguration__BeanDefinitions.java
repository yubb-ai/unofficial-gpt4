package org.springframework.boot.actuate.autoconfigure.web.servlet;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.ManagementContextFactory;

/**
 * Bean definitions for {@link ServletManagementContextAutoConfiguration}.
 */
public class ServletManagementContextAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'servletManagementContextAutoConfiguration'.
   */
  public static BeanDefinition getServletManagementContextAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ServletManagementContextAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ServletManagementContextAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean definition for 'servletWebChildContextFactory'.
   */
  public static BeanDefinition getServletWebChildContextFactoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ServletManagementContextAutoConfiguration.class);
    beanDefinition.setTargetType(ManagementContextFactory.class);
    beanDefinition.setInstanceSupplier(BeanInstanceSupplier.<ManagementContextFactory>forFactoryMethod(ServletManagementContextAutoConfiguration.class, "servletWebChildContextFactory").withGenerator((registeredBean) -> ServletManagementContextAutoConfiguration.servletWebChildContextFactory()));
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'managementServletContext'.
   */
  private static BeanInstanceSupplier<ManagementServletContext> getManagementServletContextInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ManagementServletContext>forFactoryMethod(ServletManagementContextAutoConfiguration.class, "managementServletContext", WebEndpointProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(ServletManagementContextAutoConfiguration.class).managementServletContext(args.get(0)));
  }

  /**
   * Get the bean definition for 'managementServletContext'.
   */
  public static BeanDefinition getManagementServletContextBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ManagementServletContext.class);
    beanDefinition.setInstanceSupplier(getManagementServletContextInstanceSupplier());
    return beanDefinition;
  }
}
