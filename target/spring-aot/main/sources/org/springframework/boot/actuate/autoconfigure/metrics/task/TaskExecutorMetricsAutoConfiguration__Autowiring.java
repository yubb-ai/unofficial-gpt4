package org.springframework.boot.actuate.autoconfigure.metrics.task;

import io.micrometer.core.instrument.MeterRegistry;
import java.util.Map;
import org.springframework.beans.factory.aot.AutowiredMethodArgumentsResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link TaskExecutorMetricsAutoConfiguration}.
 */
public class TaskExecutorMetricsAutoConfiguration__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static TaskExecutorMetricsAutoConfiguration apply(RegisteredBean registeredBean,
      TaskExecutorMetricsAutoConfiguration instance) {
    AutowiredMethodArgumentsResolver.forRequiredMethod("bindTaskExecutorsToRegistry", Map.class, MeterRegistry.class).resolve(registeredBean, args -> instance.bindTaskExecutorsToRegistry(args.get(0), args.get(1)));
    return instance;
  }
}
