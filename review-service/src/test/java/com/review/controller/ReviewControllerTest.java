package com.review.controller;

import com.fasterxml.jackson.core.SerializableString;
import com.review.model.ReviewModel;
import com.review.service.ReviewService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class ReviewControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReviewService reviewService;

    private final String BASE_URL = "/api/v1/review/";
    List<ReviewModel> reviews = getAll();

    static List<ReviewModel>  getAll()  {

        var reviews = new ArrayList<ReviewModel>(10);
        LongStream.range(0,10).forEach(index->{
            reviews.add(
                    new ReviewModel(index+1,(index+1)*23, (index+29)*186, "Title-"+index,""+index, LocalDateTime.now())
            );
        });
        return reviews;
    }

    @Test
    void get_all_reviews_test() throws Exception {
        Mockito.when(reviewService.findAll()).thenReturn(reviews);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(10)))
                .andDo(print());
    }

    @Test
    void get_review_by_id_test() throws Exception {
        var review = reviews.get(0);
        var url = BASE_URL+review.getId();
        when(reviewService.find(review.getId())).thenReturn(review);
        mockMvc.perform(MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(review.getId()))
                .andExpect(jsonPath("$.title").value(review.getTitle()))
                .andExpect(jsonPath("$.comment").value(review.getComment()))
                .andDo(print());

    }

    @Test
    void delete_review_by_id_test() throws Exception {
        var review = reviews.get(5);
        doNothing().when(reviewService).delete(review.getId());
        mockMvc.perform(MockMvcRequestBuilders.delete(BASE_URL+reviews.get(5)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
