package io.capdevila.poc.springcloud.demo.msb;

import static feign.FeignException.errorStatus;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomErrorDecoder implements ErrorDecoder {

  private static final Logger log = LoggerFactory.getLogger(CustomErrorDecoder.class);

  @Override
  public Exception decode(String methodKey, Response response) {
    if (response.status() >= 400 && response.status() <= 499) {
      log.error("Client error");
    } else if (response.status() >= 500 && response.status() <= 599) {
      log.error("Server error");
    }
    /* Custom exceptions should be handled nicely as Feign does*/
    return errorStatus(methodKey, response);
  }

}
