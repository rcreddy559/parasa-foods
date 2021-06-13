package com.review.service;

import com.review.model.ReviewModel;
import com.review.model.Review;
import com.review.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    ReviewService(ReviewRepository repository, ModelMapper modelMapper) {
        this.reviewRepository = repository;
        this.modelMapper = modelMapper;
    }
    public List<ReviewModel> findAll() {
        var reviews = reviewRepository.findAll();
        return reviews.stream()
                .map(review -> modelMapper.map(review, ReviewModel.class))
                .collect(Collectors.toList());
    }

    public void saveALL(List<ReviewModel> reviewModels) {
        var reviews = reviewModels.stream()
                .map(model-> modelMapper.map(model, Review.class))
                .collect(Collectors.toList());

        reviewRepository.saveAll(reviews);
    }

    public ReviewModel find(Long id) {
        var review = reviewRepository.findById(id);
        return review.isPresent()
                ? modelMapper.map(review.get(), ReviewModel.class)
                    : new ReviewModel();
    }
    public ReviewModel save(ReviewModel reviewModel) {
        var review = modelMapper.map(reviewModel, Review.class);
        var saveReview = reviewRepository.save(review);
        return modelMapper.map(saveReview, ReviewModel.class);
    }

    public ReviewModel patch(Long id, Map<String, Object> patchReview) {
        var reviewDB = reviewRepository.findById(id);
        var review = reviewDB.orElseThrow();
        patchReview.forEach((key,value)->{
            switch (key) {
                case "userId"-> review.setUserId((Long)value);
                case "productId"-> review.setProductId((Long)value);
                case "title"-> review.setTitle((String) value);
                case "comment"-> review.setComment((String) value);
            }
        });
        var reviewSave = reviewRepository.save(review);
        return modelMapper.map(reviewSave, ReviewModel.class);
    }
}
