package org.springframework.web.servlet.config.annotation;

import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.util.PathMatcher;
import org.springframework.validation.Validator;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.CompositeUriComponentsContributor;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.function.support.HandlerFunctionAdapter;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * Bean definitions for {@link WebMvcConfigurationSupport}.
 */
public class WebMvcConfigurationSupport__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'requestMappingHandlerMapping'.
   */
  private static BeanInstanceSupplier<RequestMappingHandlerMapping> getRequestMappingHandlerMappingInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RequestMappingHandlerMapping>forFactoryMethod(WebMvcConfigurationSupport.class, "requestMappingHandlerMapping", ContentNegotiationManager.class, FormattingConversionService.class, ResourceUrlProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).requestMappingHandlerMapping(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'requestMappingHandlerMapping'.
   */
  public static BeanDefinition getRequestMappingHandlerMappingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RequestMappingHandlerMapping.class);
    beanDefinition.setInstanceSupplier(getRequestMappingHandlerMappingInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mvcPatternParser'.
   */
  private static BeanInstanceSupplier<PathPatternParser> getMvcPatternParserInstanceSupplier() {
    return BeanInstanceSupplier.<PathPatternParser>forFactoryMethod(WebMvcConfigurationSupport.class, "mvcPatternParser")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).mvcPatternParser());
  }

  /**
   * Get the bean definition for 'mvcPatternParser'.
   */
  public static BeanDefinition getMvcPatternParserBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PathPatternParser.class);
    beanDefinition.setInstanceSupplier(getMvcPatternParserInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mvcUrlPathHelper'.
   */
  private static BeanInstanceSupplier<UrlPathHelper> getMvcUrlPathHelperInstanceSupplier() {
    return BeanInstanceSupplier.<UrlPathHelper>forFactoryMethod(WebMvcConfigurationSupport.class, "mvcUrlPathHelper")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).mvcUrlPathHelper());
  }

  /**
   * Get the bean definition for 'mvcUrlPathHelper'.
   */
  public static BeanDefinition getMvcUrlPathHelperBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UrlPathHelper.class);
    beanDefinition.setInstanceSupplier(getMvcUrlPathHelperInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mvcPathMatcher'.
   */
  private static BeanInstanceSupplier<PathMatcher> getMvcPathMatcherInstanceSupplier() {
    return BeanInstanceSupplier.<PathMatcher>forFactoryMethod(WebMvcConfigurationSupport.class, "mvcPathMatcher")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).mvcPathMatcher());
  }

  /**
   * Get the bean definition for 'mvcPathMatcher'.
   */
  public static BeanDefinition getMvcPathMatcherBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PathMatcher.class);
    beanDefinition.setInstanceSupplier(getMvcPathMatcherInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'viewControllerHandlerMapping'.
   */
  private static BeanInstanceSupplier<HandlerMapping> getViewControllerHandlerMappingInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<HandlerMapping>forFactoryMethod(WebMvcConfigurationSupport.class, "viewControllerHandlerMapping", FormattingConversionService.class, ResourceUrlProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).viewControllerHandlerMapping(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'viewControllerHandlerMapping'.
   */
  public static BeanDefinition getViewControllerHandlerMappingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HandlerMapping.class);
    beanDefinition.setInstanceSupplier(getViewControllerHandlerMappingInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'beanNameHandlerMapping'.
   */
  private static BeanInstanceSupplier<BeanNameUrlHandlerMapping> getBeanNameHandlerMappingInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<BeanNameUrlHandlerMapping>forFactoryMethod(WebMvcConfigurationSupport.class, "beanNameHandlerMapping", FormattingConversionService.class, ResourceUrlProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).beanNameHandlerMapping(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'beanNameHandlerMapping'.
   */
  public static BeanDefinition getBeanNameHandlerMappingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(BeanNameUrlHandlerMapping.class);
    beanDefinition.setInstanceSupplier(getBeanNameHandlerMappingInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'routerFunctionMapping'.
   */
  private static BeanInstanceSupplier<RouterFunctionMapping> getRouterFunctionMappingInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RouterFunctionMapping>forFactoryMethod(WebMvcConfigurationSupport.class, "routerFunctionMapping", FormattingConversionService.class, ResourceUrlProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).routerFunctionMapping(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'routerFunctionMapping'.
   */
  public static BeanDefinition getRouterFunctionMappingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RouterFunctionMapping.class);
    beanDefinition.setInstanceSupplier(getRouterFunctionMappingInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'resourceHandlerMapping'.
   */
  private static BeanInstanceSupplier<HandlerMapping> getResourceHandlerMappingInstanceSupplier() {
    return BeanInstanceSupplier.<HandlerMapping>forFactoryMethod(WebMvcConfigurationSupport.class, "resourceHandlerMapping", ContentNegotiationManager.class, FormattingConversionService.class, ResourceUrlProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).resourceHandlerMapping(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'resourceHandlerMapping'.
   */
  public static BeanDefinition getResourceHandlerMappingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HandlerMapping.class);
    beanDefinition.setInstanceSupplier(getResourceHandlerMappingInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mvcResourceUrlProvider'.
   */
  private static BeanInstanceSupplier<ResourceUrlProvider> getMvcResourceUrlProviderInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ResourceUrlProvider>forFactoryMethod(WebMvcConfigurationSupport.class, "mvcResourceUrlProvider")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).mvcResourceUrlProvider());
  }

  /**
   * Get the bean definition for 'mvcResourceUrlProvider'.
   */
  public static BeanDefinition getMvcResourceUrlProviderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResourceUrlProvider.class);
    beanDefinition.setInstanceSupplier(getMvcResourceUrlProviderInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'defaultServletHandlerMapping'.
   */
  private static BeanInstanceSupplier<HandlerMapping> getDefaultServletHandlerMappingInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<HandlerMapping>forFactoryMethod(WebMvcConfigurationSupport.class, "defaultServletHandlerMapping")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).defaultServletHandlerMapping());
  }

  /**
   * Get the bean definition for 'defaultServletHandlerMapping'.
   */
  public static BeanDefinition getDefaultServletHandlerMappingBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HandlerMapping.class);
    beanDefinition.setInstanceSupplier(getDefaultServletHandlerMappingInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'requestMappingHandlerAdapter'.
   */
  private static BeanInstanceSupplier<RequestMappingHandlerAdapter> getRequestMappingHandlerAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RequestMappingHandlerAdapter>forFactoryMethod(WebMvcConfigurationSupport.class, "requestMappingHandlerAdapter", ContentNegotiationManager.class, FormattingConversionService.class, Validator.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).requestMappingHandlerAdapter(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'requestMappingHandlerAdapter'.
   */
  public static BeanDefinition getRequestMappingHandlerAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RequestMappingHandlerAdapter.class);
    beanDefinition.setInstanceSupplier(getRequestMappingHandlerAdapterInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'handlerFunctionAdapter'.
   */
  private static BeanInstanceSupplier<HandlerFunctionAdapter> getHandlerFunctionAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<HandlerFunctionAdapter>forFactoryMethod(WebMvcConfigurationSupport.class, "handlerFunctionAdapter")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).handlerFunctionAdapter());
  }

  /**
   * Get the bean definition for 'handlerFunctionAdapter'.
   */
  public static BeanDefinition getHandlerFunctionAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HandlerFunctionAdapter.class);
    beanDefinition.setInstanceSupplier(getHandlerFunctionAdapterInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mvcUriComponentsContributor'.
   */
  private static BeanInstanceSupplier<CompositeUriComponentsContributor> getMvcUriComponentsContributorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<CompositeUriComponentsContributor>forFactoryMethod(WebMvcConfigurationSupport.class, "mvcUriComponentsContributor", FormattingConversionService.class, RequestMappingHandlerAdapter.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).mvcUriComponentsContributor(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'mvcUriComponentsContributor'.
   */
  public static BeanDefinition getMvcUriComponentsContributorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CompositeUriComponentsContributor.class);
    beanDefinition.setInstanceSupplier(getMvcUriComponentsContributorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'httpRequestHandlerAdapter'.
   */
  private static BeanInstanceSupplier<HttpRequestHandlerAdapter> getHttpRequestHandlerAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<HttpRequestHandlerAdapter>forFactoryMethod(WebMvcConfigurationSupport.class, "httpRequestHandlerAdapter")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).httpRequestHandlerAdapter());
  }

  /**
   * Get the bean definition for 'httpRequestHandlerAdapter'.
   */
  public static BeanDefinition getHttpRequestHandlerAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HttpRequestHandlerAdapter.class);
    beanDefinition.setInstanceSupplier(getHttpRequestHandlerAdapterInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'simpleControllerHandlerAdapter'.
   */
  private static BeanInstanceSupplier<SimpleControllerHandlerAdapter> getSimpleControllerHandlerAdapterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<SimpleControllerHandlerAdapter>forFactoryMethod(WebMvcConfigurationSupport.class, "simpleControllerHandlerAdapter")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).simpleControllerHandlerAdapter());
  }

  /**
   * Get the bean definition for 'simpleControllerHandlerAdapter'.
   */
  public static BeanDefinition getSimpleControllerHandlerAdapterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SimpleControllerHandlerAdapter.class);
    beanDefinition.setInstanceSupplier(getSimpleControllerHandlerAdapterInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'handlerExceptionResolver'.
   */
  private static BeanInstanceSupplier<HandlerExceptionResolver> getHandlerExceptionResolverInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<HandlerExceptionResolver>forFactoryMethod(WebMvcConfigurationSupport.class, "handlerExceptionResolver", ContentNegotiationManager.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).handlerExceptionResolver(args.get(0)));
  }

  /**
   * Get the bean definition for 'handlerExceptionResolver'.
   */
  public static BeanDefinition getHandlerExceptionResolverBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HandlerExceptionResolver.class);
    beanDefinition.setInstanceSupplier(getHandlerExceptionResolverInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mvcViewResolver'.
   */
  private static BeanInstanceSupplier<ViewResolver> getMvcViewResolverInstanceSupplier() {
    return BeanInstanceSupplier.<ViewResolver>forFactoryMethod(WebMvcConfigurationSupport.class, "mvcViewResolver", ContentNegotiationManager.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).mvcViewResolver(args.get(0)));
  }

  /**
   * Get the bean definition for 'mvcViewResolver'.
   */
  public static BeanDefinition getMvcViewResolverBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ViewResolver.class);
    beanDefinition.setInstanceSupplier(getMvcViewResolverInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'mvcHandlerMappingIntrospector'.
   */
  private static BeanInstanceSupplier<HandlerMappingIntrospector> getMvcHandlerMappingIntrospectorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<HandlerMappingIntrospector>forFactoryMethod(WebMvcConfigurationSupport.class, "mvcHandlerMappingIntrospector")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).mvcHandlerMappingIntrospector());
  }

  /**
   * Get the bean definition for 'mvcHandlerMappingIntrospector'.
   */
  public static BeanDefinition getMvcHandlerMappingIntrospectorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HandlerMappingIntrospector.class);
    beanDefinition.setLazyInit(true);
    beanDefinition.setInstanceSupplier(getMvcHandlerMappingIntrospectorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'viewNameTranslator'.
   */
  private static BeanInstanceSupplier<RequestToViewNameTranslator> getViewNameTranslatorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RequestToViewNameTranslator>forFactoryMethod(WebMvcConfigurationSupport.class, "viewNameTranslator")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean(WebMvcConfigurationSupport.class).viewNameTranslator());
  }

  /**
   * Get the bean definition for 'viewNameTranslator'.
   */
  public static BeanDefinition getViewNameTranslatorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RequestToViewNameTranslator.class);
    beanDefinition.setInstanceSupplier(getViewNameTranslatorInstanceSupplier());
    return beanDefinition;
  }
}
