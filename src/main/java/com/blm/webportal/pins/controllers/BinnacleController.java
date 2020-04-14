package com.blm.webportal.pins.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.blm.webportal.pins.models.entity.Binnacle;
import com.blm.webportal.pins.models.entity.service.IBinnacleService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class BinnacleController {
	
	@Autowired
	private IBinnacleService binnacleService;
	
	@GetMapping("/api/binnacle/getAll")
	public List<Binnacle> getAll(){
		return binnacleService.findAll(); 
	}
	
	@GetMapping("/api/binnacle/get/{id}")
	public Binnacle getById(@PathVariable Long id) {
		return binnacleService.findById(id);
	}
	
	@PostMapping("/api/binnacle/create")
	@ResponseStatus(HttpStatus.CREATED)
	//@ResponseStatus(HttpStatus.CREATED)
	public Binnacle create(@RequestBody Binnacle bin) {
		return binnacleService.save(bin);
	}
	
}
