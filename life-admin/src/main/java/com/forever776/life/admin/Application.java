package com.forever776.life.admin;

import com.forever7776.life.web.config.redis.JdkRedisTemplate;
import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;
import qiniu.QiNiuApi;
import util.ConfigTool;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
/**
 * @author kz
 * @date 2017年12月1日14:32:07
 */
@Configuration
@EnableAutoConfiguration
@MapperScan("com.forever7776.life.core.persistence")
@PropertySource({"classpath:application.yml", "classpath:config.properties","classpath:rabbitmq.properties"})
@ComponentScan(basePackages = "com.forever7776", includeFilters = {@ComponentScan.Filter(Aspect.class)})
//启用异步
@EnableAsync
//定时任务
@EnableScheduling
//@ImportResource({"classpath:rabbitmq.xml"})
//@ImportResource({"classpath:dubbo.xml"})
@ImportResource({"classpath:dubbo.xml", "classpath:rabbitmq.xml"})
public class Application extends SpringBootServletInitializer {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                new QiNiuApi(ConfigTool.getProp("qiniu.access"), ConfigTool.getProp("qiniu.secret"), ConfigTool.getProp("qiniu.bucket"));
                logger.info("##########七牛启动成功##########");
            }
        };
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }


    /**
     * 用于处理编码问题
     * @return
     */
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    @Bean
    public JdkRedisTemplate jdkRedisTemplate(RedisConnectionFactory connectionFactory) {
        JdkRedisTemplate jdkRedisTemplate = new JdkRedisTemplate();
        jdkRedisTemplate.setConnectionFactory(connectionFactory);
        return jdkRedisTemplate;
    }
}
