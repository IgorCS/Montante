package com.algaworks.financeiro.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@SuppressWarnings("unused")
	private String password;
	@Column(name = "enable", columnDefinition = "BOOLEAN")
	private boolean enable;
	
	@OneToMany
	private List<Autorizacao> autorizacoes;

	public List<Autorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public Usuario() {

	}

	/**
	 * @return the password
	 */
	/*
	 * public String getPassword() { return password; }
	 * 
	 * /**
	 * 
	 * @param password the password to set
	 */
	/*
	 * public void setPassword(String password) { this.password = password; }
	 */

	// getters and setters

}