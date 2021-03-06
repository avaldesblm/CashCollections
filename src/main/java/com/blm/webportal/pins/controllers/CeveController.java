package com.blm.webportal.pins.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.blm.webportal.pins.models.entity.Ceve;
import com.blm.webportal.pins.models.entity.service.ICeveService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class CeveController {
	
	@Autowired
	private ICeveService ceveService;
	
	@GetMapping("/api/ceves/getAll")
	public List<Ceve> getAll(){
		return ceveService.findAll(); 
	}
	
	@GetMapping("/api/ceves/get/{id}")
	public Ceve getById(@PathVariable Long id) {
		return ceveService.findById(id);
	}
	
	@PutMapping("/api/ceves/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Ceve edit(@RequestBody Ceve ceve, @PathVariable Long id) {
		Ceve cv = ceveService.findById(id);
		cv.setName(ceve.getName());
		cv.setHost(ceve.getHost());
		cv.setPort(ceve.getPort());
		cv.setModification_date(ceve.getModification_date());
		return ceveService.save(cv);
	}
	
}
