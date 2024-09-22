package pudding.toy.ourJourney.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pudding.toy.ourJourney.filter.AuthExceptionFilter;
import pudding.toy.ourJourney.filter.JwtAuthenticationFilter;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.CustomUserDetailService;



@Slf4j
@Configuration
public class FilterConfig{
    @Bean
    public FilterRegistrationBean<Filter> exceptionFilter(AuthService authService,CustomUserDetailService userDetailService){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new AuthExceptionFilter());
        filterFilterRegistrationBean.addUrlPatterns("/contents/*");
        filterFilterRegistrationBean.setOrder(1);
        return filterFilterRegistrationBean;
    }
    @Bean
    public FilterRegistrationBean<Filter> jwtFilter(AuthService authService,CustomUserDetailService userDetailService){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean
                = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JwtAuthenticationFilter(authService,userDetailService));
        filterFilterRegistrationBean.addUrlPatterns("/contents/*");
        filterFilterRegistrationBean.setOrder(2);
        return filterFilterRegistrationBean;
    }
}
