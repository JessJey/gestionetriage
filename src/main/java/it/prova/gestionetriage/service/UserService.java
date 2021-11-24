package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetriage.model.User;


public interface UserService {

	public List<User> listAllUtenti();
	
	public User caricaSingoloUtente(Long id);
	
	Page<User> searchAndPaginate(User userExample, Integer pageNo, Integer pageSize, String sortBy);
	
	public User aggiorna(User userInstance);

	public User inserisciNuovo(User userInstance);
	
	public void changeUserAbilitation(String userInstanceId);
	
	User findByUsername(String username);
}
