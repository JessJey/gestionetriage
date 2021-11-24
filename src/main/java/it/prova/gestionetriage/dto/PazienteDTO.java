package it.prova.gestionetriage.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetriage.model.Paziente;
import it.prova.gestionetriage.model.StatoPaziente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PazienteDTO {

	private Long id;
	private String nome;
	private String cognome;
	private String codicefiscale;

	private Date datacreazione;
	
	private StatoPaziente statoPaziente;
	
	private DottoreDTO dottore;

	public PazienteDTO(Long id, String nome, String cognome,  String codicefiscale,
			Date datacreazione,  StatoPaziente statoPaziente, DottoreDTO dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codicefiscale = codicefiscale;
		this.datacreazione = datacreazione;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public PazienteDTO( String nome,  String cognome,  String codicefiscale,
			Date datacreazione,  StatoPaziente statoPaziente, DottoreDTO dottore) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codicefiscale = codicefiscale;
		this.datacreazione = datacreazione;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public PazienteDTO( String nome,  String cognome,  String codicefiscale,
			Date datacreazione,  StatoPaziente statoPaziente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codicefiscale = codicefiscale;
		this.datacreazione = datacreazione;
		this.statoPaziente = statoPaziente;
	}
	

	public PazienteDTO(Long id, String nome, String cognome, String codicefiscale, Date datacreazione,
			StatoPaziente statoPaziente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codicefiscale = codicefiscale;
		this.datacreazione = datacreazione;
		this.statoPaziente = statoPaziente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public Date getDatacreazione() {
		return datacreazione;
	}

	public void setDatacreazione(Date datacreazione) {
		this.datacreazione = datacreazione;
	}

	public StatoPaziente getStatoPaziente() {
		return statoPaziente;
	}

	public void setStatoPaziente(StatoPaziente statoPaziente) {
		this.statoPaziente = statoPaziente;
	}

	public DottoreDTO getDottore() {
		return dottore;
	}

	public void setDottore(DottoreDTO dottore) {
		this.dottore = dottore;
	}
	
	public static PazienteDTO buildPazienteDTOFromModel(Paziente pazienteModel) {
		if(pazienteModel.getDottore() == null)
			return new PazienteDTO(pazienteModel.getId(), pazienteModel.getNome(),
					pazienteModel.getCognome(), pazienteModel.getCodicefiscale(),pazienteModel.getDatacreazione(),
					pazienteModel.getStatoPaziente());
		
		return new PazienteDTO(pazienteModel.getId(), pazienteModel.getNome(),
				pazienteModel.getCognome(), pazienteModel.getCodicefiscale(),pazienteModel.getDatacreazione(),
				pazienteModel.getStatoPaziente(),DottoreDTO.buildDottoreDTOFromModel(pazienteModel.getDottore()));
	}

	public static List<PazienteDTO> createPazienteDTOListFromModelList(List<Paziente> modelListInput) {
		return modelListInput.stream().map(pazienteEntity -> {
			return PazienteDTO.buildPazienteDTOFromModel(pazienteEntity);
		}).collect(Collectors.toList());
	}

	public Paziente buildPazienteModel() {
		if (this.dottore == null)
			return new Paziente(this.id, this.nome, this.cognome,this.codicefiscale, this.datacreazione, 
					this.statoPaziente);

		return new Paziente(this.id, this.nome, this.cognome, this.codicefiscale, this.datacreazione,
				this.statoPaziente, this.dottore.buildDottoreModel());
	}
}
