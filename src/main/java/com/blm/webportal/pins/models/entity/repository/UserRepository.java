package com.blm.webportal.pins.models.entity.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.blm.webportal.pins.models.entity.User;

@RepositoryRestResource(path="users")
public interface UserRepository extends PagingAndSortingRepository<User, Long>{
	
	//findByUsernameAndPassword
	public User findByUsername(String username);
	
	//Agregar atributos de la clase creada, no del nombre del campo en la tabla
	@Query("select u from User u where u.username=?1 and u.name=?2")
	public User encontrarPorUsername(String username, String name);
	
	
	public User findByMail(String mail);
	
}
