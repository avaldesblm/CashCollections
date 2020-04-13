package com.blm.webportal.pins.models.entity.service;

import java.util.List;

import com.blm.webportal.pins.models.entity.Ceve;

public interface ICeveService {
	
	public List<Ceve> findAll();
	public Ceve findById(Long id);
	public Ceve save(Ceve ceve);
	
}
