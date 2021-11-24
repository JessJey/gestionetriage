package it.prova.gestionetriage.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Dottore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreDTO {

	private Long id;

	private String nome;

	private String cognome;

	private String codiceDipendente;

	private PazienteDTO pazienteAttualmenteInVisita;

	public DottoreDTO() {
		// TODO Auto-generated constructor stub
	}

	public DottoreDTO(Long id, String nome, String cognome, String codiceDipendente,
			PazienteDTO pazienteAttualmenteInVisita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;
	}

	public DottoreDTO(String nome, String cognome, String codiceDipendente, PazienteDTO pazienteAttualmenteInVisita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pazienteAttualmenteInVisita = pazienteAttualmenteInVisita;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceDipendente() {
		return codiceDipendente;
	}

	public void setCodiceDipendente(String codiceDipendente) {
		this.codiceDipendente = codiceDipendente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Dottore buildDottoreModel() {
		if (this.pazienteAttualmenteInVisita == null)
			return new Dottore(this.id, this.nome, this.cognome, this.codiceDipendente, null);
		return new Dottore(this.id, this.nome, this.cognome, this.codiceDipendente,
				this.pazienteAttualmenteInVisita.buildPazienteModel());
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore dottoreModel) {
		if (dottoreModel.getPazienteAttualmenteInVisita() == null)
			return new DottoreDTO(dottoreModel.getId(), dottoreModel.getNome(), dottoreModel.getCognome(),
					dottoreModel.getCodiceDipendente(), null);

		return new DottoreDTO(dottoreModel.getId(), dottoreModel.getNome(), dottoreModel.getCognome(),
				dottoreModel.getCodiceDipendente(),
				PazienteDTO.buildPazienteDTOFromModel(dottoreModel.getPazienteAttualmenteInVisita()));

	}

	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> modelListInput) {
		return modelListInput.stream().map(dottoreEntity -> {
			DottoreDTO result = DottoreDTO.buildDottoreDTOFromModel(dottoreEntity);

			return result;
		}).collect(Collectors.toList());
	}

}
