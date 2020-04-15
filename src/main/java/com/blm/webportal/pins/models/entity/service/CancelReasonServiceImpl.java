package com.blm.webportal.pins.models.entity.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blm.webportal.pins.models.entity.CancelReasons;
import com.blm.webportal.pins.models.entity.repository.CancelReasonRepository;

@Service
public class CancelReasonServiceImpl implements ICancelReasonService{
	
	@Autowired
	private CancelReasonRepository reasonDao;
	
	@Override
	public List<CancelReasons> findAll() {
		return (List<CancelReasons>) reasonDao.findAll();
	}

	@Override
	public CancelReasons findById(Long id) {
		return reasonDao.findById(id).orElse(null);
	}

	@Override
	public CancelReasons save(CancelReasons reason) {
		return reasonDao.save(reason);
	}

	@Override
	public List<CancelReasons> findAllEnabled() {
		return (List<CancelReasons>) reasonDao.findByEnable(1);
	}

}
