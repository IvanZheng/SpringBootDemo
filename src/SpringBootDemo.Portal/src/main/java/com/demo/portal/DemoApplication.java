package com.demo.portal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan("com.demo")
@EnableJpaRepositories("com.demo")
@EntityScan("com.demo.*")
@MapperScan("com.demo.application.dao")
@EnableCaching
//@ImportResource(locations={"classpath:spring-mybatis.xml"})
public class DemoApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(DemoApplication.class);
    }
}


