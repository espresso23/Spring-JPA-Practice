package com.fpt.jpapractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.fpt.model")
@EnableJpaRepositories("com.fpt.repository")
@ComponentScan(basePackages = {"com.fpt.controller", "com.fpt.service"})
public class JpapracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpapracticeApplication.class, args);
		openHomePage(); // Mở trang web chính (localhost:8080/spmvc/addStudent) khi chạy chương trình
	}

	public static void openHomePage() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080/spmvc/addStudent");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
