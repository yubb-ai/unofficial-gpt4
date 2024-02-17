package org.springframework.boot.sql.init.dependency;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DatabaseInitializationDependencyConfigurer}.
 */
public class DatabaseInitializationDependencyConfigurer__BeanDefinitions {
  /**
   * Bean definitions for {@link DatabaseInitializationDependencyConfigurer.DependsOnDatabaseInitializationPostProcessor}.
   */
  public static class DependsOnDatabaseInitializationPostProcessor {
    /**
     * Get the bean definition for 'dependsOnDatabaseInitializationPostProcessor'.
     */
    public static BeanDefinition getDependsOnDatabaseInitializationPostProcessorBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(DatabaseInitializationDependencyConfigurer.DependsOnDatabaseInitializationPostProcessor.class);
      beanDefinition.setInstanceSupplier(DatabaseInitializationDependencyConfigurer.DependsOnDatabaseInitializationPostProcessor::new);
      return beanDefinition;
    }
  }
}
