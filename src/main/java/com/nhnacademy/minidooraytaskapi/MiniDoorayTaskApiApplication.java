package com.nhnacademy.minidooraytaskapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.nhnacademy.minidooraytaskapi.tag.entity")
public class MiniDoorayTaskApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniDoorayTaskApiApplication.class, args);
	}

}
