package com.blm.webportal.pins.models.entity.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blm.webportal.pins.aop.Trace;
import com.blm.webportal.pins.models.entity.Binnacle;
import com.blm.webportal.pins.models.entity.repository.BinnacleRepository;

@Service
public class BinnacleServiceImpl implements IBinnacleService{
	
	@Autowired
	private BinnacleRepository binnacleDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Binnacle> findAll() {
		return (List<Binnacle>) binnacleDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Binnacle findById(Long id) {
		return binnacleDao.findById(id).orElse(null);
	}
	
	@Trace
	@Override
	@Transactional
	public Binnacle save(Binnacle ceve) {
		return binnacleDao.save(ceve);
	}

}
