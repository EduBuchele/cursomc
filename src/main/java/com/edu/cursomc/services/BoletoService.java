package com.edu.cursomc.services;

import com.edu.cursomc.domain.Cidade;
import com.edu.cursomc.domain.Cliente;
import com.edu.cursomc.domain.Endereco;
import com.edu.cursomc.domain.PagamentoComBoleto;
import com.edu.cursomc.domain.enums.TipoCliente;
import com.edu.cursomc.dto.ClienteDTO;
import com.edu.cursomc.dto.ClienteNewDTO;
import com.edu.cursomc.repositories.ClienteRepository;
import com.edu.cursomc.repositories.EnderecoRepository;
import com.edu.cursomc.services.exceptions.DataIntegrityException;
import com.edu.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BoletoService {

	@Autowired
	private ClienteRepository clienteDAO;


	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
