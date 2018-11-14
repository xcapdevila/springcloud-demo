package io.capdevila.poc.springcloud.demo.msb;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsaResource {

  private final MsaProxy msaProxy;

  public MsaResource(MsaProxy msaProxy) {
    this.msaProxy = msaProxy;
  }

  @GetMapping("/msa")
  public String get(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
    return msaProxy.get(propKey);
  }

  @GetMapping("/msa/timeout")
  public String getTimeout(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
    String response = null;
    try {
      response = msaProxy.getTimeout(propKey);
    } catch (RuntimeException e) {
      if (e.getCause() instanceof SocketException) {
        response = e.getCause().getClass().getName();
      } else if (e.getCause() instanceof SocketTimeoutException) {
        response = e.getCause().getClass().getName();
      } else {
        response = "Unmapped cause " + e.getCause().getClass().getName();
      }
    }
    return response;
  }

  @GetMapping("/msa/exception")
  public String getException(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
    return msaProxy.getException(propKey);
  }

  @GetMapping("/msa/notFound")
  public ResponseEntity<String> getNotFound(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
    return msaProxy.getNotFound(propKey);
  }

  @GetMapping("/msa/unauthorized")
  public ResponseEntity<String> getUnauthorized(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
    return msaProxy.getUnauthorized(propKey);
  }

  @PostMapping("/msa/{propKey}")
  public String post(@PathVariable("propKey") String propKey) {
    return msaProxy.post(propKey);
  }

}
