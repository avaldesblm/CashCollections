package com.blm.webportal.pins.models.entity.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blm.webportal.pins.models.entity.CancelReasons;

public interface CancelReasonRepository extends JpaRepository<CancelReasons, Long>{
	
	public List<CancelReasons> findByEnable(int i);

}
