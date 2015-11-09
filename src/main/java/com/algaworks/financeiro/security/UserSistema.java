package com.algaworks.financeiro.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.algaworks.financeiro.model.Usuario;

public class UserSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	public UserSistema(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		//super();
		super(usuario.getNome(), usuario.getSenha(), authorities);
		this.usuario = usuario;
		System.out.println("Nome do Usuário:"+usuario.getNome());
	}

	public Usuario getUsuarioNovo() {
		return usuario;
	}

}
