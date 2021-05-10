package com.spring.loja.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.loja.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	Usuario findByEmail(String email);

	Usuario findByUsername(String username);
}
