package in.naman.springtutorials.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handlerSecurityException(Exception exception) {
        ProblemDetail errorDetail = null;

        log.error("handlerSecurityException::An error occurred", exception);

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(401), exception.getMessage());
            errorDetail.setProperty("description", "Email or password is invalid.");
        }

        if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "The account is locked.");
        }

        if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "Not allowed to access this resource.");
        }
        if (exception instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "Invalid JWT signature.");
        }

        if (exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(403), exception.getMessage());
            errorDetail.setProperty("description", "Expired JWT token.");
        }

        if (errorDetail == null) {
            errorDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatusCode.valueOf(500), exception.getMessage());
            errorDetail.setProperty("description", "Unknown internal server error");
        }

        return errorDetail;
    }
}
