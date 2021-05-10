package com.spring.loja.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.loja.model.Role;
import com.spring.loja.repository.RoleRepository;
import com.spring.loja.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByRole(String role) {
		return roleRepository.findByRole(role);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
