package com.edu.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cursomc.domain.Cliente;
import com.edu.cursomc.repositories.ClienteRepository;
import com.edu.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository dao;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = dao.findById(id);
		return obj.orElseThrow(() -> 
						new ObjectNotFoundException("Objeto não encontrado Id: " + id 
								+ ", Tipo: " + Cliente.class.getName()));
	}

}
