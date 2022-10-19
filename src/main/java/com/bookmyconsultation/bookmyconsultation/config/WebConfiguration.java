package com.bookmyconsultation.bookmyconsultation.config;

import com.bookmyconsultation.bookmyconsultation.servlet.AuthFilter;
import com.bookmyconsultation.bookmyconsultation.servlet.CorsFilter;
import com.bookmyconsultation.bookmyconsultation.servlet.RequestContextFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.bookmyconsultation.bookmyconsultation.controller")
public class WebConfiguration {

    @Autowired
    private AuthFilter authFilter;
    @Bean
    public FilterRegistrationBean authFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authFilter);
        registration.addUrlPatterns("/*");
        registration.setName("Auth Filter");
        registration.setOrder(3);
        return registration;
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CorsFilter());
        registration.addUrlPatterns("/*");
        registration.setName("Cors Filter");
        registration.setOrder(0);
        return registration;
    }


    @Bean
    public FilterRegistrationBean reqContextFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RequestContextFilter());
        registration.addUrlPatterns("/*");
        registration.setName("reqContext Filter");
        registration.setOrder(1);
        return registration;
    }
}
