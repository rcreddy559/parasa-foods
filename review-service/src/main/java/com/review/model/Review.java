package com.review.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long productId;
    private String title;
    private String comment;
    private LocalDateTime dateTime;

    public Review(Long id, Long userId, Long productId, String title, String comment, LocalDateTime dateTime) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.title = title;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    public Review() {
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
