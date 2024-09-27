package pudding.toy.ourJourney.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
public class AuthExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
            return;
        } catch (ResponseStatusException ex) {
            // 인증 관련 예외만 필터에서 처리
            response.setStatus(ex.getStatusCode().value());
            String reason = ex.getReason() != null ? ex.getReason() : "Error occurred";
            response.getWriter().write(reason);
        } catch (Exception e) {
            throw e;
        }
    }
}
