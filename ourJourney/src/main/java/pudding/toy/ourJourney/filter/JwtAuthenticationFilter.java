package pudding.toy.ourJourney.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
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
    private static final String[] exceptURIs = {
            "/swagger", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs/**", "/v3/api-docs/**",
            "/contents/*", "/categories", "/contents", "/tags", "/health"
    };
    private static final String regex = "^/profiles/\\d+$";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        try {
            if ((isExceptUrl(requestUrl) && "GET".equalsIgnoreCase(method)) || (requestUrl.matches("/profiles") && "POST".equalsIgnoreCase(method))) {
                System.out.println("필터적용안한다잇");
                chain.doFilter(request, response);
                return;
            }
            System.out.println("필터적용한다잇");
            AuthResponse authResponse = authService.validateAuth(request.getHeader("Authorization"));
            setAuthenticationInContext(authResponse);

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    private void setAuthenticationInContext(AuthResponse authResponse) {
        System.out.println("UserDetail profileID" + userDetailService.loadUserByUserId(authResponse.getUserId()).getAuthorities());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetailService.loadUserByUserId(authResponse.getUserId()),
                "",
                userDetailService.loadUserByUserId(authResponse.getUserId()).getAuthorities());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private boolean isExceptUrl(String requestUrl) {
        if (requestUrl.matches(regex)) {
            return true; //get id에만 적용하지 않음.
        }
        return PatternMatchUtils.simpleMatch(exceptURIs, requestUrl);
    }
}
