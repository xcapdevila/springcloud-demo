package io.capdevila.poc.springcloud.demo.msb;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static feign.FeignException.errorStatus;

public class CustomErrorDecoder implements ErrorDecoder {

	public static Logger log = LoggerFactory.getLogger(CustomErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() >= 400 && response.status() <= 499) {
			log.error("Client error");
		} else if (response.status() >= 500 && response.status() <= 599) {
			log.error("Server error");
		} else {
			log.error("No error?");
		}
		return errorStatus(methodKey, response);
	}

}
