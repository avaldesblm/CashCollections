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

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserFeignClient client;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.blm.webportal.pins.models.entity.User us = client.findByUsername(username);
		if(us == null) {
			throw new UsernameNotFoundException("Error en el login, no existe el usuario");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
		authorities.add(new SimpleGrantedAuthority(us.getRole().getName()));
		
		return new User(username,
				us.getPassword(), true, 
				true, true, true, authorities);
	}
}
