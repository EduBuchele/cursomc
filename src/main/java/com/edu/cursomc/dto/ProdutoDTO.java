package com.edu.cursomc.dto;

import com.edu.cursomc.domain.Cliente;
import com.edu.cursomc.domain.Produto;
import com.edu.cursomc.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ProdutoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double preco;

	public ProdutoDTO() {}

	public ProdutoDTO(Produto p) {
		this.id = p.getId();
		this.nome = p.getNome();
		this.preco = p.getPreco();
	}

	public ProdutoDTO(Integer id, String nome, Double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}
