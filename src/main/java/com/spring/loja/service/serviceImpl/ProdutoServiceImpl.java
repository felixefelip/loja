package com.spring.loja.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.loja.model.Produto;
import com.spring.loja.model.Usuario;
import com.spring.loja.repository.ProdutoRepository;
import com.spring.loja.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	@Override
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}

	@Override
	public List<Produto> findAllByAutor(Usuario autor) {
		return produtoRepository.findAllByCadastrador(autor);
	}

	@Override
	public Produto findById(Long id) {
		return produtoRepository.findById(id).get();
	}

	@Override
	public Produto save(Produto post) {
		return produtoRepository.save(post);
	}

	@Override
	public void remove(Produto post) {
		produtoRepository.delete(post);

	}

}
