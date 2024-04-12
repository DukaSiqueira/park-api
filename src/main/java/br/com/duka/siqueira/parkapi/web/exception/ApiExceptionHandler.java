package br.com.duka.siqueira.parkapi.web.exception;

import br.com.duka.siqueira.parkapi.exception.UserNameUniqueViolationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result) {
        log.error("API error: ", ex);
        return ResponseEntity
                .status(UNPROCESSABLE_ENTITY)
                .contentType(APPLICATION_JSON)
                .body(new ErrorMessage(request, UNPROCESSABLE_ENTITY,
                        "Invalid attributes", result));
    }

    @ExceptionHandler(UserNameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> userNameUniqueViolationException(UserNameUniqueViolationException ex,
                                                                        HttpServletRequest request) {
        log.error("API error: ", ex);
        return ResponseEntity
                .status(CONFLICT)
                .contentType(APPLICATION_JSON)
                .body(new ErrorMessage(request, CONFLICT,
                        ex.getMessage()));
    }

}
