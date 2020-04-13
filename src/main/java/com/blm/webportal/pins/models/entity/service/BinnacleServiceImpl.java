package com.blm.webportal.pins.models.entity.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	/*
	@Override
	public List<Binnacle> findByCeve(String ceve) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Binnacle> findByRequest_date(Date request_date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Binnacle> findByBimbo_code(String bimbo_code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Binnacle> findByRoute(String route) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	@Override
	@Transactional
	public Binnacle save(Binnacle ceve) {
		return binnacleDao.save(ceve);
	}

}
