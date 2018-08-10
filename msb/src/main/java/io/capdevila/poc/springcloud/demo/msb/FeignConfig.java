package io.capdevila.poc.springcloud.demo.msb;

import feign.Request;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

	/**
	 * This the default behaviour:
	 *
	 * @return ErrorDecoder
	 * @Bean public ErrorDecoder feignErrorDecoder() {
	 * return new feign.codec.ErrorDecoder.Default();
	 * }
	 */
	@Bean
	public ErrorDecoder feignErrorDecoder() {
		return new CustomErrorDecoder();
	}

	@Bean
	public Request.Options requestOptions(@Value("${custom.feign.connect.timeout:2000}") int connectTimeoutMillis,
			@Value("${custom.feign.read.timeout:5000}") int readTimeoutMillis) {
		return new Request.Options(connectTimeoutMillis, readTimeoutMillis);
	}

}
