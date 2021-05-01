package com.edu.cursomc.resources;

import com.edu.cursomc.domain.Categoria;
import com.edu.cursomc.domain.Pedido;
import com.edu.cursomc.domain.Produto;
import com.edu.cursomc.dto.CategoriaDTO;
import com.edu.cursomc.dto.ProdutoDTO;
import com.edu.cursomc.services.PedidoService;
import com.edu.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}



	@RequestMapping( method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue = "") String nome,
			@RequestParam(value="categorias", defaultValue = "") String categorias,
			@RequestParam(value="page", defaultValue = "0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value="direction", defaultValue = "ASC") String direction) {


		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecode = URL.decodeParam(nome);
		Page<Produto> list= service.search(nomeDecode, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(o -> new ProdutoDTO(o));
		return ResponseEntity.ok().body(listDTO);
	}

	
	
}
