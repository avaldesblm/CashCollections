package com.blm.webportal.pins.models.entity.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.blm.webportal.pins.models.entity.Ceve;
import com.blm.webportal.pins.models.entity.repository.CeveRepository;

@Service
public class CeveServiceImpl implements ICeveService{
	
	@Autowired
	private CeveRepository ceveDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Ceve> findAll() {
		return (List<Ceve>) ceveDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ceve findById(Long id) {
		return ceveDao.findById(id).orElse(null);
	}

	@Override
	public Ceve save(Ceve ceve) {
		return ceveDao.save(ceve);
	}

}
