package com.gpt4.copilot.controller;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link chatController}.
 */
public class chatController__BeanDefinitions {
  /**
   * Get the bean definition for 'chatController'.
   */
  public static BeanDefinition getChatControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(chatController.class);
    beanDefinition.setInstanceSupplier(chatController::new);
    return beanDefinition;
  }
}
