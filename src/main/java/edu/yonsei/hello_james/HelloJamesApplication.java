package edu.yonsei.hello_james;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HelloJamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloJamesApplication.class, args);
		System.out.println("good");
	}

}
