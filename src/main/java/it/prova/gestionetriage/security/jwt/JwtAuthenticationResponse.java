package it.prova.gestionetriage.security.jwt;

import java.util.Date;
import java.util.List;

public class JwtAuthenticationResponse {

	private String token;
	private String type = "Bearer";
	private String username;
	private String nome;
	private String cognome;
	private Date dataCreazione;
	private List<String> roles;

	public JwtAuthenticationResponse(String accessToken, String username, String nome, String cognome, Date dataCreazione, List<String> roles) {
		this.token = accessToken;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.dataCreazione = dataCreazione;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public List<String> getRoles() {
		return roles;
	}
}