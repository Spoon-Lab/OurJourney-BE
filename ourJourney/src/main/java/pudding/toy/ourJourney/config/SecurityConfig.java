package pudding.toy.ourJourney.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pudding.toy.ourJourney.filter.AuthExceptionFilter;
import pudding.toy.ourJourney.filter.JwtAuthenticationFilter;
import pudding.toy.ourJourney.service.CustomUserDetailService;
import pudding.toy.ourJourney.service.AuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthService authService, CustomUserDetailService userDetailService) {
        return new JwtAuthenticationFilter(authService, userDetailService);
    }
    public AuthExceptionFilter authExceptionFilter() {
        return new AuthExceptionFilter();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthService authService, CustomUserDetailService userDetailService) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll());
        http
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .addFilterBefore(jwtAuthenticationFilter(authService,userDetailService), UsernamePasswordAuthenticationFilter.class);
        http
                .addFilterBefore(authExceptionFilter(), JwtAuthenticationFilter.class);
        return http.build();
    }
}
