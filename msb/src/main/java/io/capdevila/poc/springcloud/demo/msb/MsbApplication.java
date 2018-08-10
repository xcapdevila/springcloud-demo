package io.capdevila.poc.springcloud.demo.msb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsbApplication.class, args);
	}
}
