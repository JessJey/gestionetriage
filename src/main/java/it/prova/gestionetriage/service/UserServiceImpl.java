package it.prova.gestionetriage.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import it.prova.gestionetriage.exceptions.UserNotFoundException;
import it.prova.gestionetriage.model.Stato;
import it.prova.gestionetriage.model.User;
import it.prova.gestionetriage.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> listAllUtenti() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User caricaSingoloUtente(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Element with id " + id + " not found."));
	}

	@Override
	public Page<User> searchAndPaginate(User userExample, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<User> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(userExample.getUsername()))
				predicates.add(
						cb.like(cb.upper(root.get("USERNAME")), "%" + userExample.getUsername().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(userExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("NOME")), "%" + userExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(userExample.getCognome()))
				predicates.add(
						cb.like(cb.upper(root.get("COGNOME")), "%" + userExample.getCognome().toUpperCase() + "%"));

			if (userExample.getDataCreazione() != null)
				predicates
						.add(cb.greaterThanOrEqualTo((root.get("DATACREAZIONE")),userExample.getDataCreazione()));

			if (userExample.getStato() != null)
				predicates.add(cb.like(cb.upper(root.get("STATO")), "%" + userExample.getStato() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return userRepository.findAll(specificationCriteria, paging);
	}

	@Override
	public void aggiorna(User userInstance) {
		userRepository.save(userInstance);

	}

	@Override
	public void inserisciNuovo(User userInstance) {
		userRepository.save(userInstance);

	}

	@Override
	public void changeUserAbilitation(Long userInstanceId) {
		
		User userInstance = caricaSingoloUtente(userInstanceId);
		
		if(userInstance.getStato().equals(Stato.ATTIVO)) {
			userInstance.setStato(Stato.DISABILITATO);
		}
		if(userInstance.getStato().equals(Stato.CREATO)) {
			userInstance.setStato(Stato.DISABILITATO);
		}
	}

}
