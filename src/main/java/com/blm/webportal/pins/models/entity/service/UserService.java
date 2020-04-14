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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.blm.webportal.pins.aop.Trace;
import com.blm.webportal.pins.clients.UserFeignClient;

@Service
public class UserService implements IUsuarioService, UserDetailsService{

	@Autowired
	private UserFeignClient client;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Trace
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String[] usernameAndDomain = username.split("\\|");

		if (usernameAndDomain == null || usernameAndDomain.length != 3) {
            throw new UsernameNotFoundException("Username, ceveId and the blm fields must be provided");
        }
		
		boolean isBlmUser = Boolean.parseBoolean(usernameAndDomain[2]);
		if(isBlmUser) {
			com.blm.webportal.pins.models.entity.User us = client.findByMail(usernameAndDomain[0]);
			if(us == null) {
				throw new UsernameNotFoundException("Error en el login, no existe el usuario");
			}
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
			authorities.add(new SimpleGrantedAuthority(us.getRole().getName()));
			
			return new User(username,
					us.getPassword(), true, 
					true, true, true, authorities);
		} else {
			
			/*
			//Buscar en ceve db
			
			if(us == null) {
				throw new UsernameNotFoundException("Error en el login, no existe el usuario");
			}
			*/
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(); 
			authorities.add(new SimpleGrantedAuthority("ROLE_CEVE"));
			
			String pass = "12345";
			String passwordBCrypt = passwordEncoder.encode(pass);
			
			return new User(usernameAndDomain[0], passwordBCrypt, 
					true, true, true, true, authorities);
		}
	}

	@Override
	public com.blm.webportal.pins.models.entity.User findByUsername(String username) throws UsernameNotFoundException {
		try {
			return client.findByMail(username);
		} catch(Exception e){
			throw new UsernameNotFoundException("Error en el login, no existe el usuario");
		} 
	}

}
