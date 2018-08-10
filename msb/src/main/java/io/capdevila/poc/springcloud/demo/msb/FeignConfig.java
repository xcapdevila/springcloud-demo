package io.capdevila.poc.springcloud.demo.msb;

import feign.Contract;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

//	@Bean
//	public ErrorDecoder feignErrorDecoder() {
//		return new feign.codec.ErrorDecoder.Default();
//	}

	@Bean
	public ErrorDecoder feignErrorDecoder() {
		return new CustomErrorDecoder();
	}

}
