package com.blm.webportal.pins.models.entity.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.blm.webportal.pins.models.entity.User;

@RepositoryRestResource(path="users")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	//findByUsernameAndPassword
	public User findByUsername(String username);
	
	public User findByMail(String mail);
	
}
