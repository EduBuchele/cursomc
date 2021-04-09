package com.edu.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edu.cursomc.domain.Categoria;
import com.edu.cursomc.dto.CategoriaDTO;
import com.edu.cursomc.repositories.CategoriaRepository;
import com.edu.cursomc.services.exceptions.DataIntegrityException;
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
		Categoria newObjt = find(obj.getId());
		updateData(newObjt, obj);
		find(obj.getId());
		return dao.save(obj);
	}

	public void delete(Integer id) {
		try {
			dao.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new DataIntegrityException("Não é possível excluir categoria que contem produtos vinculados.");
		}
		
	}

	public List<Categoria> findAll() {
		return dao.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return dao.findAll(pageRequest);
	}
	
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
	
	private void updateData(Categoria newObjt, Categoria obj) {
		newObjt.setNome(obj.getNome());
	}
	
}
