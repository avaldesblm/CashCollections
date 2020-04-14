package com.blm.webportal.pins.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.blm.webportal.pins.models.entity.User;

@FeignClient(name="cashcollection-service",url="localhost:8001")
public interface UserFeignClient {
	
	@GetMapping("users/search/findByUsername")
	public User findByUsername(@RequestParam String username);
	
	@GetMapping("users/search/findByMail")
	public User findByMail(@RequestParam String mail);
	
}
