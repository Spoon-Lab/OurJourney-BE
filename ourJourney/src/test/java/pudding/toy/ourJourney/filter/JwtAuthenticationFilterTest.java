package pudding.toy.ourJourney.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.context.SecurityContext;
import pudding.toy.ourJourney.config.FilterConfig;
import pudding.toy.ourJourney.dto.auth.AuthResponse;
import pudding.toy.ourJourney.service.AuthService;


import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {
    HttpServletRequest request;
    HttpServletResponse response;
    FilterChain filterChain;
    FilterConfig jwtAuthenticationFilter;
    AuthResponse authResponse;
    AuthService authService;
    SecurityContext securityContext;


    @BeforeEach
    void setUp(){
        jwtAuthenticationFilter = Mockito.mock(FilterConfig.class);
        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        filterChain = Mockito.mock(FilterChain.class);
        authResponse = Mockito.spy(new AuthResponse(1L,null, true, "user"));
        securityContext = Mockito.mock(SecurityContext.class);
        authService =  Mockito.mock(AuthService.class);
    }
    @Test
    @DisplayName("토큰을 지닌 유저가 요청을 하면 filter가 수행되는가.")
    void securityContextTest() throws ServletException, IOException {
        when(authService.validateAuth(any())).thenReturn(authResponse);

    }
}