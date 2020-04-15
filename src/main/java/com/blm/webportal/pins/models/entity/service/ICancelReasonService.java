package com.blm.webportal.pins.models.entity.service;

import java.util.List;
import com.blm.webportal.pins.models.entity.CancelReasons;

public interface ICancelReasonService {
	
	public List<CancelReasons> findAll();
	public CancelReasons findById(Long id);
	public List<CancelReasons> findAllEnabled();
	public CancelReasons save(CancelReasons reason);
	
}
