package it.prova.gestionetriage.security.repository;


import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionetriage.model.User;



public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User>  {
	User findByUsername(String username);

}