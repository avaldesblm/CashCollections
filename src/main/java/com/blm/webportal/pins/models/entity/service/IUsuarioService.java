package com.blm.webportal.pins.models.entity.service;

import com.blm.webportal.pins.models.entity.User;

public interface IUsuarioService {
	
	public User findByUsername(String username);
	
}
