package com.spring.loja.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.loja.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByRole(String role);

}
