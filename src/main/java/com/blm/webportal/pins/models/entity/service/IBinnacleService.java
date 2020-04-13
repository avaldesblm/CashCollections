package com.blm.webportal.pins.models.entity.service;

import java.util.Date;
import java.util.List;
import com.blm.webportal.pins.models.entity.Binnacle;

public interface IBinnacleService {
	
	public List<Binnacle> findAll();
	public Binnacle findById(Long id);/*
	public List<Binnacle> findByCeve(String ceve);
	public List<Binnacle> findByRequest_date(Date request_date);
	public List<Binnacle> findByBimbo_code(String bimbo_code);
	public List<Binnacle> findByRoute(String route);*/
	public Binnacle save(Binnacle ceve);
	
}
