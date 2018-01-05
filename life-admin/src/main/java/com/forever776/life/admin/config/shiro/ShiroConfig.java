package com.forever776.life.admin.config.shiro;

import com.forever776.life.admin.config.shiro.filter.UrlPermissionsFilter;
import com.forever7776.life.web.config.shiro.realm.MyShiroRealm;
import com.google.common.collect.Maps;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.Map;

/**
 * @author kz
 * shiro配置
 */
@Configuration
public class ShiroConfig {

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setUnauthorizedUrl("/403");

        Map<String, Filter>filters = Maps.newHashMap();
        filters.put("perms", urlPermissionsFilter());
        filters.put("anon", new AnonymousFilter());
        bean.setFilters(filters);

        Map<String, String> chains = Maps.newHashMap();
        chains.put("/tools/**", "anon");
        chains.put("/**", "perms");
        bean.setFilterChainDefinitionMap(chains);
        return bean;
    }

    /**
     * @see org.apache.shiro.mgt.SecurityManager
     * @return
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm());
        manager.setCacheManager(cacheManager());
        manager.setSessionManager(defaultWebSessionManager());
        return manager;
    }

    /**
     * @see DefaultWebSessionManager
     * @return
     */
    @Bean(name="sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager());
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    /**
     * @return
     */
    @Bean
    @DependsOn(value="lifecycleBeanPostProcessor")
    public MyShiroRealm userRealm() {
        MyShiroRealm userRealm = new MyShiroRealm();
        userRealm.setCacheManager(cacheManager());
        return userRealm;
    }

    @Bean
    public UrlPermissionsFilter urlPermissionsFilter() {
        return new UrlPermissionsFilter();
    }

    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}