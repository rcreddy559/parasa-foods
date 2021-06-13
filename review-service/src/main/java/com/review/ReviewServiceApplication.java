package com.review;

import com.review.model.ReviewModel;
import com.review.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.LongStream;

@SpringBootApplication
public class ReviewServiceApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ReviewServiceApplication.class, args);
	}

	@Autowired
	private ReviewService reviewService;


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		var reviews = new ArrayList<ReviewModel>(20);
		LongStream.range(1,20).forEach(index->{
			reviews.add(
					new ReviewModel(index*23, index*186, "Title-"+index,""+index, LocalDateTime.now())
			);
		});

		reviewService.saveALL(reviews);
	}
}
