package org.springframework.boot.autoconfigure.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link GsonAutoConfiguration}.
 */
public class GsonAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'gsonAutoConfiguration'.
   */
  public static BeanDefinition getGsonAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GsonAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(GsonAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'gsonBuilder'.
   */
  private static BeanInstanceSupplier<GsonBuilder> getGsonBuilderInstanceSupplier() {
    return BeanInstanceSupplier.<GsonBuilder>forFactoryMethod(GsonAutoConfiguration.class, "gsonBuilder", List.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(GsonAutoConfiguration.class).gsonBuilder(args.get(0)));
  }

  /**
   * Get the bean definition for 'gsonBuilder'.
   */
  public static BeanDefinition getGsonBuilderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GsonBuilder.class);
    beanDefinition.setInstanceSupplier(getGsonBuilderInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'gson'.
   */
  private static BeanInstanceSupplier<Gson> getGsonInstanceSupplier() {
    return BeanInstanceSupplier.<Gson>forFactoryMethod(GsonAutoConfiguration.class, "gson", GsonBuilder.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(GsonAutoConfiguration.class).gson(args.get(0)));
  }

  /**
   * Get the bean definition for 'gson'.
   */
  public static BeanDefinition getGsonBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(Gson.class);
    beanDefinition.setInstanceSupplier(getGsonInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'standardGsonBuilderCustomizer'.
   */
  private static BeanInstanceSupplier<GsonAutoConfiguration.StandardGsonBuilderCustomizer> getStandardGsonBuilderCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<GsonAutoConfiguration.StandardGsonBuilderCustomizer>forFactoryMethod(GsonAutoConfiguration.class, "standardGsonBuilderCustomizer", GsonProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(GsonAutoConfiguration.class).standardGsonBuilderCustomizer(args.get(0)));
  }

  /**
   * Get the bean definition for 'standardGsonBuilderCustomizer'.
   */
  public static BeanDefinition getStandardGsonBuilderCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(GsonAutoConfiguration.StandardGsonBuilderCustomizer.class);
    beanDefinition.setInstanceSupplier(getStandardGsonBuilderCustomizerInstanceSupplier());
    return beanDefinition;
  }
}
