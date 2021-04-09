package com.edu.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.cursomc.domain.Cidade;
import com.edu.cursomc.domain.Cliente;
import com.edu.cursomc.domain.Endereco;
import com.edu.cursomc.domain.enums.TipoCliente;
import com.edu.cursomc.dto.ClienteDTO;
import com.edu.cursomc.dto.ClienteNewDTO;
import com.edu.cursomc.repositories.ClienteRepository;
import com.edu.cursomc.repositories.EnderecoRepository;
import com.edu.cursomc.services.exceptions.DataIntegrityException;
import com.edu.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteDAO;
	
	@Autowired
	private EnderecoRepository enderecoDAO;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = clienteDAO.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public Cliente update(Cliente obj) {
		Cliente newObjt = find(obj.getId());
		updateData(newObjt, obj);
		return clienteDAO.save(obj);
	}

	private void updateData(Cliente newObjt, Cliente obj) {
		newObjt.setNome(obj.getNome());
		newObjt.setEmail(obj.getEmail());
	}

	public void delete(Integer id) {
		try {
			clienteDAO.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir cliente que contem pedidos vinculados.");
		}

	}

	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteDAO.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	

	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEndereco().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());	
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());	
		}
		
		return cli;
		
		
		
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj =  clienteDAO.save(obj);
		enderecoDAO.saveAll(obj.getEndereco());
		return obj;
	}

}
