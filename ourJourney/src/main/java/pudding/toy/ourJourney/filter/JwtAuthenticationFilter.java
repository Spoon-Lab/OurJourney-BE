package pudding.toy.ourJourney.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;
import pudding.toy.ourJourney.auth.dto.AuthResponse;
import pudding.toy.ourJourney.auth.service.AuthService;
import pudding.toy.ourJourney.auth.service.CustomUserDetailService;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final AuthService authService;
    private final CustomUserDetailService userDetailService;
    private static final String[] exceptURIs = {
            "/swagger", "/swagger-ui.html", "/swagger-ui/*", "/api-docs", "/api-docs/**", "/v3/api-docs/**",
            "/contents/*", "/categories", "/contents", "/tags", "/health", "/tags", "/"
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
            if (!isTokenInHeader(request)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "토큰 값이 없습니다.");
            }
            AuthResponse authResponse = authService.validateAuth(request.getHeader("Authorization"));
            setAuthenticationInContext(authResponse);

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    //    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        try {
//            //헤더에 토큰이 있을 경우.
//            if (isTokenInHeader(request)) {
//                AuthResponse authResponse = authService.validateAuth(request.getHeader("Authorization"));
//                setAuthenticationInContext(authResponse);
//                chain.doFilter(request, response);
//                return;
//            }
//            //헤더에 토큰이 없을 경우. 비회원 처리
//            setAnonymousAuthenticationInContext();
//            chain.doFilter(request, response);
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
    private boolean isTokenInHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        return authorizationHeader != null && !authorizationHeader.isEmpty();
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

//    private void setAnonymousAuthenticationInContext() {
//        Authentication anonymousAuth = new AnonymousAuthenticationToken(
//                "anonymousUserKey",
//                "anonymousUser",
//                AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS"));
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(anonymousAuth);
//        SecurityContextHolder.setContext(context);
//    }

    private boolean isExceptUrl(String requestUrl) {
        if (requestUrl.matches(regex)) {
            return true; //get id에만 적용하지 않음.
        }
        return PatternMatchUtils.simpleMatch(exceptURIs, requestUrl);
    }
}
