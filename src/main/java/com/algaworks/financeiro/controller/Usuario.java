package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Named
@SessionScoped
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private Date dataLogin;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isLogado() {
		return nome != null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataLogin() {
		return dataLogin;
	}

	public void setDataLogin(Date dataLogin) {
		this.dataLogin = dataLogin;
	}

}
