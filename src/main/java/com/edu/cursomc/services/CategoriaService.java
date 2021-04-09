package com.edu.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cursomc.domain.Categoria;
import com.edu.cursomc.repositories.CategoriaRepository;
import com.edu.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository dao;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = dao.findById(id);
		return obj.orElseThrow(() -> 
						new ObjectNotFoundException("Objeto não encontrado Id: " + id 
								+ ", Tipo: " + Categoria.class.getName()));
	}

	
	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return dao.save(obj);
	}


	public Categoria update(Categoria obj) {
		find(obj.getId());
		return dao.save(obj);
	}
}
