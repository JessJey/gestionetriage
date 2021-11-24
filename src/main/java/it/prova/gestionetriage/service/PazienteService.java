package it.prova.gestionetriage.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionetriage.model.Paziente;


public interface PazienteService {

	public Page<Paziente> searchAndPaginate(Paziente pazienteExample, Integer pageNo, Integer pageSize, String sortBy);

	public List<Paziente> listAll();

	public Paziente get(Long idInput);
	
	public Paziente save(Paziente input);
	
	public void delete(Paziente input);
	
	public Paziente findByCodiceFiscale(String codiceFiscale);

}