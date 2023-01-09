package TDT.backend.common.auth.jwt;

import TDT.backend.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        Exception exception = (Exception) request.getAttribute("exception");
        log.info("exception: {}", exception.getMessage());
        sendErrorResponse(response, exception);
        logExceptionMessage(authException, exception);

    }

    private void logExceptionMessage(AuthenticationException authException, Exception exception) {
        String message = exception != null ? exception.getMessage() : authException.getMessage();
        log.warn("Unauthorized error happened: {}", message);
    }

    public static void sendErrorResponse(HttpServletResponse response, Exception exception) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse;

        if (exception.getClass().equals(ExpiredJwtException.class)) errorResponse = ErrorResponse.of(401, "만료된 토큰입니다.");
        else if (exception.getClass().equals(SignatureException.class)) errorResponse = ErrorResponse.of(401, "유효하지 않은 토큰입니다.");
        else errorResponse = ErrorResponse.of(401, exception.getMessage());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorResponse.getStatus());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
