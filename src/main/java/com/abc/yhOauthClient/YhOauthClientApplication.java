package com.abc.yhOauthClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.abc")
@SpringBootApplication
public class YhOauthClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(YhOauthClientApplication.class, args);
	}
}
