package it.prova.gestionetriage.controller.api;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetriage.dto.PazienteDTO;
import it.prova.gestionetriage.exceptions.PazienteNotDimessoException;
import it.prova.gestionetriage.exceptions.PazienteNotFoundException;
import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;
import it.prova.gestionetriage.service.PazienteService;

@RestController
@RequestMapping(value = "/paziente", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PazienteRestController {

	@Autowired
	PazienteService pazienteService;
	
	
	@GetMapping
	public List<Paziente> getAll() {
		return pazienteService.listAll();
	}
	
	@GetMapping("/{id}")
	public PazienteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Paziente paziente = pazienteService.get(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);

		return PazienteDTO.buildPazienteDTOFromModel(paziente);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Paziente createNew(@Valid @RequestBody Paziente pazienteInput) {
		pazienteInput.setStatoPaziente(StatoPaziente.IN_ATTESA_VISITA);
		pazienteInput.setDatacreazione(new Date());
		return pazienteService.save(pazienteInput);
	}
	
	@PutMapping("/{id}")
	public Paziente update(@Valid @RequestBody Paziente pazienteInput, @PathVariable(required = true) Long id) {
		Paziente pazienteToUpdate = pazienteService.get(id);
		if(pazienteInput.getNome() != null) {
			pazienteToUpdate.setNome(pazienteInput.getNome());
		}
		if(pazienteInput.getCognome() != null) {
			pazienteToUpdate.setCognome(pazienteInput.getCognome());
		}
		if(pazienteInput.getCodicefiscale() != null) {
			pazienteToUpdate.setCodicefiscale(pazienteInput.getCodicefiscale());
		}
		if(pazienteInput.getDatacreazione() != null) {
			pazienteToUpdate.setDatacreazione(pazienteInput.getDatacreazione());
		}
		if(pazienteInput.getStatoPaziente() != null) {
			pazienteToUpdate.setStatoPaziente(pazienteInput.getStatoPaziente());
		}
		if(pazienteInput.getDottore() != null) {
			pazienteToUpdate.setDottore(pazienteInput.getDottore());
		}
		return pazienteService.save(pazienteToUpdate);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Paziente paziente = pazienteService.get(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);
		if (!paziente.getStatoPaziente().equals(StatoPaziente.DIMESSO))
			throw new PazienteNotDimessoException("Paziente non dimesso");
		pazienteService.delete(paziente);
	}
	
	@PostMapping("/search")
	public ResponseEntity<Page<Paziente>> searchAndPagination(@RequestBody Paziente pazienteExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {

		Page<Paziente> results = pazienteService.searchAndPaginate(pazienteExample, pageNo, pageSize, sortBy);

		return new ResponseEntity<Page<Paziente>>(results, new HttpHeaders(), HttpStatus.OK);
	}
}
