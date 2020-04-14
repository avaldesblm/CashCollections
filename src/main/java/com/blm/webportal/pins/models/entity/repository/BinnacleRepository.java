package com.blm.webportal.pins.models.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.blm.webportal.pins.models.entity.Binnacle;

public interface BinnacleRepository extends JpaRepository<Binnacle, Long>{

}
