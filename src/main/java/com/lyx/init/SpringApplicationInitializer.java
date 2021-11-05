package com.lyx.init;

import com.lyx.config.ApplicationConfig;
import com.lyx.config.WebConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    protected WebApplicationContext createRootApplicationContext()
    {
        return super.createRootApplicationContext();
    }

    @Override
    protected WebApplicationContext createServletApplicationContext()
    {
        return super.createServletApplicationContext();
    }

    /**
     * 相当于 url-mapping
     */
    @Override
    protected String[] getServletMappings()
    {
        return new String[]{"/"};
    }

    /**
     * 加载 applicationContext.xml
     */
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[]{ApplicationConfig.class};
    }

    /**
     * 加载springmvc.xml
     */
    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[]{WebConfig.class};
    }
}
