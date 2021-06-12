package com.userservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Collections;

@ControllerAdvice
@Slf4j
public class UserServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleUserNotfound(Exception e, WebRequest webRequest) {
        log.error("User not found with message: "+e.getLocalizedMessage());
        final var response = ErrorResponse.of(HttpStatus.NOT_FOUND.value(),
                                                   "#TH234", Collections.singletonList(e.getLocalizedMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(URLExceptionHandler.class)
    public final ResponseEntity<ErrorResponse> handleUURLExceptionHandler(Exception e, WebRequest webRequest) {
        log.error("Matching URL not found: "+e.getLocalizedMessage());
        final var response = ErrorResponse.of(HttpStatus.NOT_FOUND.value(),
                "#TH2344", Collections.singletonList(e.getLocalizedMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleDefaultException(Exception e,
                                                                      WebRequest webRequest) {
        log.error("Default Exception handler: "+e.getLocalizedMessage());
        final var response = ErrorResponse.of(HttpStatus.BAD_REQUEST.value(),
                "#TH234 ", Collections.singletonList(e.getLocalizedMessage()));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
