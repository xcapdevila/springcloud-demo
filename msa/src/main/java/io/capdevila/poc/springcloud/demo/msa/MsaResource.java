package io.capdevila.poc.springcloud.demo.msa;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class MsaResource {

	private final Environment environment;

	public MsaResource(Environment environment) {
		this.environment = environment;
	}

	@GetMapping
	public String get(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
		return getResponse(HttpMethod.GET.name(), propKey);
	}

	@GetMapping("timeout")
	public String getTimeout(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
		try {
			Thread.sleep(120_000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return getResponse(HttpMethod.GET.name(), propKey);
	}

	@GetMapping("exception")
	public String getException(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
		throw new RuntimeException(propKey);
	}

	@GetMapping("notFound")
	public ResponseEntity<String> getNotFound(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("unauthorized")
	public ResponseEntity<String> getUnauthorized(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@PostMapping("{propKey}")
	public String post(@PathVariable("propKey") String propKey) {
		return getResponse(HttpMethod.POST.name(), propKey);
	}

	private String getResponse(String httpMethod, String propKey) {
		return String.format("Received %s in %s:%s%nProperty %s: %s", httpMethod, environment.getProperty("spring.application.name"),
				environment.getProperty("server.port"), propKey, environment.getProperty(propKey));
	}

}

