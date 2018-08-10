package io.capdevila.poc.springcloud.demo.msb;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "springcloud-demo-msa", url = "localhost:9001", configuration = FeignConfig.class) //decode404
//@RibbonClient(name = "springcloud-demo-msa")
public interface MsaProxy {

	@GetMapping
	String get(@RequestParam(name = "propKey", defaultValue = "spring.application.name") String propKey);

	@PostMapping("{propKey}")
	String post(@PathVariable("propKey") String propKey);

}
