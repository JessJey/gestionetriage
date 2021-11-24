package it.prova.gestionetriage.service;

import java.util.ArrayList;
import java.util.Date;
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
import it.prova.gestionetriage.security.repository.UserRepository;

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
						cb.like(cb.upper(root.get("username")), "%" + userExample.getUsername().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(userExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + userExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(userExample.getCognome()))
				predicates.add(
						cb.like(cb.upper(root.get("cognome")), "%" + userExample.getCognome().toUpperCase() + "%"));

			if (userExample.getDataCreazione() != null)
				predicates
						.add(cb.greaterThanOrEqualTo((root.get("datacreazione")),userExample.getDataCreazione()));

			if (userExample.getStato() != null)
				predicates.add(cb.like(cb.upper(root.get("stato")), "%" + userExample.getStato() + "%"));

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return userRepository.findAll(specificationCriteria, paging);
	}

	@Override
	public User aggiorna(User userInstance) {
		return userRepository.save(userInstance);

	}

	@Override
	public User inserisciNuovo(User userInstance) {
		userInstance.setDataCreazione(new Date());
	return	userRepository.save(userInstance);

	}

	@Override
	public void changeUserAbilitation(String userInstanceId) {
		
		User userInstance = findByUsername(userInstanceId);
		userInstance.setStato(Stato.DISABILITATO);
		userRepository.save(userInstance);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
