package pudding.toy.ourJourney.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pudding.toy.ourJourney.dto.auth.AuthResponse;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.CustomUserDetailService;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private final CustomUserDetailService userDetailService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        AuthResponse authResponse = authService.validateAuth(request.getHeader("Authorization"));
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetailService.loadUserByUserId(authResponse.getUserId()),
                "",
                userDetailService.loadUserByUserId(authResponse.getUserId()).getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
