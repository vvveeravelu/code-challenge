package com.vcorp.codechallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vcorp.codechallenge.config.LoadCityConfig;


/**
 * @author VVEERAVELU
 *
 */
@SpringBootApplication
public class CodeChallengeApplication {
	 @Autowired
	 LoadCityConfig LoadCityConfig;
	public static void main(String[] args) {
		SpringApplication.run(CodeChallengeApplication.class, args);
	}

}
