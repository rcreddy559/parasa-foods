package com.review.model;

import java.time.LocalDateTime;

public class ReviewModel {
    private Long id;
    private Long userId;
    private Long productId;
    private String title;
    private String comment;
    private LocalDateTime dateTime;

    public ReviewModel(Long id, Long userId, Long productId, String title, String comment, LocalDateTime dateTime) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.title = title;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public ReviewModel(Long userId, Long productId, String title, String comment, LocalDateTime dateTime) {
        this.userId = userId;
        this.productId = productId;
        this.title = title;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public ReviewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
