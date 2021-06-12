package com.userservice;

import com.userservice.model.User;
import com.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.Random;

@SpringBootApplication
@Slf4j
public class UserServiceApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
//		IntStream.range(1, 1000).forEach(value->{
//			var user1 = new User("User-"+value,
//					value+"123-34, thalapula palli",
//					"india",
//					 value * getRandomNumberUsingInts() * 439.38,
//					(long) getRandomNumberUsingInts(,"Thala Pula"+(i++), "India"+(i++)));
//			userRepository.save(user1);
//			log.info(user1.toString(,"Thala Pula"+(i++), "India"+(i++)));
//		});

		var users = new ArrayList<User>();
		var i = 209;
		users.add(new User(111L, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(122L, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(133L, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(144L, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(155L, "Nima Roy", 27, "Female", "HR", 2013, 22700.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(166L, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(177L, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(188L, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(199L, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(200L, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(211L, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(222L, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(233L, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(244L, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(255L, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(266L, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0,"Thala Pula"+(i++), "India"+(i++)));
		users.add(new User(277L, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0,"Thala Pula"+(i++), "India"+(i)));
		userRepository.saveAll(users);
	}

	private int getRandomNumberUsingInts() {
		return new Random().ints(1,10).findFirst().orElse(2);
	}
}
