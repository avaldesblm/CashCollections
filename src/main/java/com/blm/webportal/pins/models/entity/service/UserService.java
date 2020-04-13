package com.blm.webportal.pins.models.entity.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.blm.webportal.pins.clients.UserFeignClient;

import feign.FeignException;

@Service
public class UserService implements IUsuarioService, UserDetailsService{

	@Autowired
	private UserFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			com.blm.webportal.pins.models.entity.User us = client.findByUsername(username);
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
			authorities.add(new SimpleGrantedAuthority(us.getRole().getName()));
			return new User(username,
					us.getPassword(), true, 
					true, true, true, authorities);
		} catch (FeignException e) {
			throw new UsernameNotFoundException("Error en el login, no existe el usuario");
		}
	}

	@Override
	public com.blm.webportal.pins.models.entity.User findByUsername(String username) {
		return client.findByUsername(username);
	}
}
