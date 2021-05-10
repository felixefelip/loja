package com.spring.loja.service;

import com.spring.loja.model.Role;

public interface RoleService {
	Role findByRole(String role);

	Role save(Role role);
}
