package io.capdevila.poc.springcloud.demo.msb;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController
public class MsaResource {

	private final MsaProxy msaProxy;
	private final Environment environment;

	public MsaResource(MsaProxy msaProxy, Environment environment) {
		this.msaProxy = msaProxy;
		this.environment = environment;
	}

	@GetMapping("/msa")
	public String get(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey) {
		return msaProxy.get(propKey);
	}

	@PostMapping("/msa/{propKey}")
	public String post(@PathVariable("propKey") String propKey) {
		return msaProxy.post(propKey);
	}

}
