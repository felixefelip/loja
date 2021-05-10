package com.spring.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.loja.model.Produto;
import com.spring.loja.model.Usuario;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	List<Produto> findAllByCadastrador(Usuario cadastrador);
}
