package com.seecen.config;

import com.seecen.filter.MyFilter;
import com.seecen.interceptor.UserInterceptor;
import com.seecen.resolver.MyHandlerExceptionResolver;
import com.seecen.resolver.UserResolver;
import com.seecen.web.MyMessageConverter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //自定义控制器方法参数解析器
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserResolver());
    }
    //自定义http消息转化器
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MyMessageConverter());
    }
    //自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor()).addPathPatterns("/*");
    }
    //自定义控制器异常解析器,也可以直接在类方法加注解，不用在此配置，参考ExceptionHandler1类
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
    }
    @Bean
    FilterRegistrationBean filterRegistrationBean(){
            FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
            filterRegistrationBean.setFilter(new MyFilter());
            filterRegistrationBean.addUrlPatterns("/*");
            return filterRegistrationBean;
    }
}
