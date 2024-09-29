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
        } catch (Exception ex) {
            // 인증 관련 예외만 필터에서 처리
            if (ex instanceof ResponseStatusException e) {
                response.setStatus(e.getStatusCode().value());
                String reason = e.getReason() != null ? e.getReason() : "Authentication failed";
                response.getWriter().write(reason);
                response.setCharacterEncoding("UTF-8"); // 응답 인코딩 설정
                response.setContentType("application/json; charset=UTF-8"); // 응답 타입 설정
                return;
            }
            throw ex;
        }
    }
}
