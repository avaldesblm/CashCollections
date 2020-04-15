package com.blm.webportal.pins.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blm.webportal.pins.models.entity.Binnacle;
import com.blm.webportal.pins.models.entity.CancelReasons;
import com.blm.webportal.pins.models.entity.service.ICancelReasonService;

@RestController
public class CancelReasonController {
	
	@Autowired
	private ICancelReasonService reasonService;
	
	@GetMapping("/api/reasons/getAll")
	public List<CancelReasons> getAll(){
		return reasonService.findAll();
	}
	
	@GetMapping("/api/reasons/get/{id}")
	public CancelReasons getById(@PathVariable Long id){
		return reasonService.findById(id);
	}
	
	@PutMapping("/api/reasons/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public CancelReasons edit(@RequestBody CancelReasons reason, @PathVariable Long id) {
		CancelReasons cr = reasonService.findById(id);
		cr.setName(reason.getName());
		cr.setDescription(reason.getDescription());
		cr.setEnable(reason.getEnable());
		return reasonService.save(cr);
	}
	
	@GetMapping("/api/reasons/getAllActive")
	public List<CancelReasons> getActive(){
		return reasonService.findAllEnabled();
	}
	
	@PostMapping("/api/reasons/create")
	@ResponseStatus(HttpStatus.CREATED)
	public CancelReasons create(@RequestBody CancelReasons reason) {
		return reasonService.save(reason);
	}
	
}
