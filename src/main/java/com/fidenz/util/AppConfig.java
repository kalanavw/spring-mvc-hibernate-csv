package com.fidenz.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Kalana on May, 2018
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fidenz")
public class AppConfig implements WebMvcConfigurer {
   @Bean
    public InternalResourceViewResolver viewResolver(){
       InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
       viewResolver.setPrefix("/");
       viewResolver.setSuffix(".jsp");
       return viewResolver;
   }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
