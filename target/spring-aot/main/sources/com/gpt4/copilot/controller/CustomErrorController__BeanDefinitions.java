package com.gpt4.copilot.controller;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CustomErrorController}.
 */
public class CustomErrorController__BeanDefinitions {
  /**
   * Get the bean definition for 'customErrorController'.
   */
  public static BeanDefinition getCustomErrorControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CustomErrorController.class);
    beanDefinition.setInstanceSupplier(CustomErrorController::new);
    return beanDefinition;
  }
}
