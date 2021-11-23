package it.prova.gestionetriage.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paziente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String codicefiscale;
	private Date datacreazione;
	@Enumerated(EnumType.STRING)
	private StatoPaziente statoPaziente;
	private Dottore dottore;
	
	public Paziente() {
		super();
	}

	public Paziente(Long id, String nome, String cognome, String codicefiscale, Date datacreazione,
			StatoPaziente statoPaziente, Dottore dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codicefiscale = codicefiscale;
		this.datacreazione = datacreazione;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public Paziente(String nome, String cognome, String codicefiscale, Date datacreazione, StatoPaziente statoPaziente,
			Dottore dottore) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codicefiscale = codicefiscale;
		this.datacreazione = datacreazione;
		this.statoPaziente = statoPaziente;
		this.dottore = dottore;
	}

	public Paziente(String nome, String cognome, String codicefiscale, Date datacreazione,
			StatoPaziente statoPaziente) {
		super();
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

	public Dottore getDottore() {
		return dottore;
	}

	public void setDottore(Dottore dottore) {
		this.dottore = dottore;
	}

	
}
