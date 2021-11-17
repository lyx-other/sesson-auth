package com.lyx.config;

import com.lyx.interceptor.SimpleAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.List;

/**
 * 相当于 springmvc.xml
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.lyx", includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class)})
public class WebConfig implements WebMvcConfigurer
{
    @Autowired
    @Qualifier("simpleAuthenticationInterceptor")
    private SimpleAuthenticationInterceptor simpleAuthenticationInterceptor;

    /**
     * 视图解析器
     */
    @Bean
    public InternalResourceViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer pathMatchConfigurer)
    {

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer contentNegotiationConfigurer)
    {

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer asyncSupportConfigurer)
    {

    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer)
    {

    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry)
    {

    }

    /**
     * 添加拦截器
     * @param interceptorRegistry interceptorRegistry
     */
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry)
    {
        interceptorRegistry.addInterceptor(simpleAuthenticationInterceptor).addPathPatterns("/r/**"); // 只有请求 /r/** 的时候这个拦截器才起作用.
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry)
    {

    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry)
    {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("login");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry)
    {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list)
    {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list)
    {

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> list)
    {

    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> list)
    {

    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> list)
    {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> list)
    {

    }

    @Override
    public Validator getValidator()
    {
        return null;
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver()
    {
        return null;
    }
}
