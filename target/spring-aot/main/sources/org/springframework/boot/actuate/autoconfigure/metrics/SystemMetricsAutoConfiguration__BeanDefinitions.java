package org.springframework.boot.actuate.autoconfigure.metrics;

import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.actuate.metrics.system.DiskSpaceMetricsBinder;

/**
 * Bean definitions for {@link SystemMetricsAutoConfiguration}.
 */
public class SystemMetricsAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'systemMetricsAutoConfiguration'.
   */
  public static BeanDefinition getSystemMetricsAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SystemMetricsAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(SystemMetricsAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'uptimeMetrics'.
   */
  private static BeanInstanceSupplier<UptimeMetrics> getUptimeMetricsInstanceSupplier() {
    return BeanInstanceSupplier.<UptimeMetrics>forFactoryMethod(SystemMetricsAutoConfiguration.class, "uptimeMetrics")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(SystemMetricsAutoConfiguration.class).uptimeMetrics());
  }

  /**
   * Get the bean definition for 'uptimeMetrics'.
   */
  public static BeanDefinition getUptimeMetricsBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UptimeMetrics.class);
    beanDefinition.setInstanceSupplier(getUptimeMetricsInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'processorMetrics'.
   */
  private static BeanInstanceSupplier<ProcessorMetrics> getProcessorMetricsInstanceSupplier() {
    return BeanInstanceSupplier.<ProcessorMetrics>forFactoryMethod(SystemMetricsAutoConfiguration.class, "processorMetrics")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(SystemMetricsAutoConfiguration.class).processorMetrics());
  }

  /**
   * Get the bean definition for 'processorMetrics'.
   */
  public static BeanDefinition getProcessorMetricsBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ProcessorMetrics.class);
    beanDefinition.setInstanceSupplier(getProcessorMetricsInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'fileDescriptorMetrics'.
   */
  private static BeanInstanceSupplier<FileDescriptorMetrics> getFileDescriptorMetricsInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<FileDescriptorMetrics>forFactoryMethod(SystemMetricsAutoConfiguration.class, "fileDescriptorMetrics")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(SystemMetricsAutoConfiguration.class).fileDescriptorMetrics());
  }

  /**
   * Get the bean definition for 'fileDescriptorMetrics'.
   */
  public static BeanDefinition getFileDescriptorMetricsBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FileDescriptorMetrics.class);
    beanDefinition.setInstanceSupplier(getFileDescriptorMetricsInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'diskSpaceMetrics'.
   */
  private static BeanInstanceSupplier<DiskSpaceMetricsBinder> getDiskSpaceMetricsInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<DiskSpaceMetricsBinder>forFactoryMethod(SystemMetricsAutoConfiguration.class, "diskSpaceMetrics", MetricsProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(SystemMetricsAutoConfiguration.class).diskSpaceMetrics(args.get(0)));
  }

  /**
   * Get the bean definition for 'diskSpaceMetrics'.
   */
  public static BeanDefinition getDiskSpaceMetricsBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DiskSpaceMetricsBinder.class);
    beanDefinition.setInstanceSupplier(getDiskSpaceMetricsInstanceSupplier());
    return beanDefinition;
  }
}
