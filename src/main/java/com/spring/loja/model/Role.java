package com.spring.loja.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(unique = true)
	private String role;

	@ManyToMany(mappedBy = "listaRoles", fetch = FetchType.LAZY)
	private Collection<Usuario> listaUsuarios;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Collection<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(Collection<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

}
