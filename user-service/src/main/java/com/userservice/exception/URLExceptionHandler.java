package com.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class URLExceptionHandler extends RuntimeException {
    public URLExceptionHandler(String message) {super(message);}
}
