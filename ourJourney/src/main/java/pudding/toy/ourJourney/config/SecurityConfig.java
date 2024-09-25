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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pudding.toy.ourJourney.filter.AuthExceptionFilter;
import pudding.toy.ourJourney.filter.JwtAuthenticationFilter;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.CustomUserDetailService;

import java.util.Collections;
import java.util.List;

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
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://our-journey-fe.vercel.app"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthService authService, CustomUserDetailService userDetailService) throws Exception {
        http
                .cors(corsCustomizer -> corsCustomizer.configurationSource(configurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll());
        http
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .addFilterBefore(jwtAuthenticationFilter(authService, userDetailService), UsernamePasswordAuthenticationFilter.class);
        http
                .addFilterBefore(authExceptionFilter(), JwtAuthenticationFilter.class);
        return http.build();
    }
}
