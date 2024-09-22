package pudding.toy.ourJourney.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pudding.toy.ourJourney.filter.JwtAuthenticationFilter;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthService authService;
    private final CustomUserDetailService userDetailService;

    JwtAuthenticationFilter jwtAuthenticationFilter(AuthService authService, CustomUserDetailService userDetailService) {
        return new JwtAuthenticationFilter(authService, userDetailService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.GET, "/{contentId}/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/{contentId}/**").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/{contentId}/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/{contentId}/**").authenticated()
                .anyRequest().authenticated());
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtAuthenticationFilter(authService, userDetailService), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
