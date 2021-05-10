package com.spring.loja.service;

import java.util.List;

import com.spring.loja.model.Produto;
import com.spring.loja.model.Usuario;

public interface ProdutoService {

	List<Produto> findAll();

	List<Produto> findAllByAutor(Usuario autor);

	Produto findById(Long id);

	Produto save(Produto post);

	void remove(Produto post);

}
