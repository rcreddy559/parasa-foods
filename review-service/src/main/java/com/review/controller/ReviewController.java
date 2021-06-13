package com.review.controller;

import com.review.model.ReviewModel;
import com.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    ReviewController(ReviewService service) {
        this.reviewService = service;
    }

    @GetMapping
    public List<ReviewModel> getAll() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ReviewModel get(@PathVariable Long id) {
        return reviewService.find(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }

}
