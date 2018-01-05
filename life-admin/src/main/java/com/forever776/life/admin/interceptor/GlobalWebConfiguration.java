package com.forever776.life.admin.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author kz
 * @date 2017年12月1日14:32:07
 * @desc 为后台操作链接增加权限控制，只有指定用户可以访问后台操作页面
 */
@Configuration
public class GlobalWebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccessLinkInterceptor());
    }
}