package com.edu.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cursomc.domain.Pedido;
import com.edu.cursomc.repositories.PedidoRepository;
import com.edu.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository dao;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = dao.findById(id);
		return obj.orElseThrow(() -> 
						new ObjectNotFoundException("Objeto não encontrado Id: " + id 
								+ ", Tipo: " + Pedido.class.getName()));
	}

}
