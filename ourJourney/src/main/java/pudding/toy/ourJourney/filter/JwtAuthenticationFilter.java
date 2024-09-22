package pudding.toy.ourJourney.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import pudding.toy.ourJourney.dto.auth.AuthResponse;
import pudding.toy.ourJourney.service.AuthService;
import pudding.toy.ourJourney.service.CustomUserDetailService;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private final CustomUserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        try{
            if ("/contents/**".equals(requestUrl) && "GET".equalsIgnoreCase(method)){
                chain.doFilter(request,response);
            }
            AuthResponse authResponse = authService.validateAuth(request.getHeader("Authorization"));
            setAuthenticationInContext(authResponse);
            chain.doFilter(request,response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("AuthorizationFilter is applied");
        }
    }

    private void setAuthenticationInContext(AuthResponse authResponse) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetailService.loadUserByUserId(authResponse.getUserId()),
                "",
                userDetailService.loadUserByUserId(authResponse.getUserId()).getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
