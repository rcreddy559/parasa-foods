package com.userservice.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    private int status;
    private String title;
    private LocalDateTime dateTime;
    private List<String> descriptions;

    public ErrorResponse(int status, String title, List<String> descriptions) {
        this.status = status;
        this.title = title;
        this.descriptions =descriptions;
        this.dateTime = LocalDateTime.now();
    }

    public static ErrorResponse of(int status, String title, List<String> descriptions) {
        return new ErrorResponse(status, title, descriptions);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }
}
