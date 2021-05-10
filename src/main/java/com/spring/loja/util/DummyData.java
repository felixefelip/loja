package com.spring.loja.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.loja.model.Produto;
import com.spring.loja.model.Role;
import com.spring.loja.model.Usuario;
import com.spring.loja.service.ProdutoService;
import com.spring.loja.service.RoleService;
import com.spring.loja.service.UsuarioService;

@Component
public class DummyData {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RoleService roleService;

	@Autowired
	ProdutoService produtoService;

	@PostConstruct
	public void savePosts() throws IOException {

		// criarRoles();
		// criarNovosUsuarios();
		// criarNovosProdutos();
		criarPastas();
	}

	public void criarNovosUsuarios() {
		Role role = roleService.findByRole("ADMIN");

		Usuario usuarioAdmin = new Usuario();

		usuarioAdmin.setNome("Felipe Felix Pessoa");
		usuarioAdmin.setPassword("27867169");
		usuarioAdmin.setCpf("173.320.777-52");
		usuarioAdmin.setEmail("felippe.felix98@gmail.com");
		usuarioAdmin.setUsername("felipemugen");
		usuarioAdmin.setListaRoles(Arrays.asList(role));

		usuarioService.save(usuarioAdmin);

		Usuario usuarioNormal = new Usuario();

		usuarioNormal.setNome("Maria Silva");
		usuarioNormal.setPassword("123456");
		usuarioNormal.setEmail("maria.silva@gmail.com");
		usuarioNormal.setCpf("888.888.888-88");
		usuarioNormal.setUsername("mariasilva");
		usuarioNormal.setListaRoles(Arrays.asList(role));

		usuarioService.save(usuarioNormal);
	}

	public void criarRoles() {
		Role roleAdmin = new Role();
		roleAdmin.setRole("ADMIN");

		Role roleUser = new Role();
		roleUser.setRole("USER");

		roleService.save(roleAdmin);
		roleService.save(roleUser);
	}

	public void criarNovosProdutos() {
		Usuario usuario = usuarioService.findById(3);

		Produto p1 = new Produto();
		p1.setCadastrador(usuario);
		p1.setData(LocalDate.now());
		p1.setDescricao("Docker");
		p1.setImagem("imagem");

		produtoService.save(p1);

	}

	public void criarPastas() throws IOException {
		File file = new File("produtoimagens");

		if (!file.exists()) {
			file.mkdirs();
		}
	}

}