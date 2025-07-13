package com.booking;

import org.springframework.boot.SpringApplication;
import java.util.TimeZone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // 👈 Force UTC for all date/time

	}

}
