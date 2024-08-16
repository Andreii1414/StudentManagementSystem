package com.example.demo.Jwt;

import com.example.demo.Jwt.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/home", "/isAdmin", "/top10", "/diriginti", "/top5", "/burse", "/tipBurse"
                , "/clase", "/adaugaElev", "/mutaElev", "/materii", "/motiveazaAbsenta");
        return registrationBean;
    }
}