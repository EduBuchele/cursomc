package com.edu.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.edu.cursomc.domain.Cliente;
import com.edu.cursomc.dto.ClienteDTO;
import com.edu.cursomc.repositories.ClienteRepository;
import com.edu.cursomc.services.exceptions.DataIntegrityException;
import com.edu.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository dao;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = dao.findById(id);
		return obj.orElseThrow(() -> 
						new ObjectNotFoundException("Objeto não encontrado Id: " + id 
								+ ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) {
		Cliente newObjt = find(obj.getId());
		updateData(newObjt, obj);
		return dao.save(obj);
	}

	private void updateData(Cliente newObjt, Cliente obj) {
		newObjt.setNome(obj.getNome());
		newObjt.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		try {
			dao.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir cliente que contem pedidos vinculados.");
		}
		
	}

	public List<Cliente> findAll() {
		return dao.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return dao.findAll(pageRequest);
	}
	
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
}
