package com.edu.cursomc.services;

import java.util.Date;
import java.util.Optional;

import com.edu.cursomc.domain.ItemPedido;
import com.edu.cursomc.domain.PagamentoComBoleto;
import com.edu.cursomc.domain.enums.EstadoPagamento;
import com.edu.cursomc.repositories.ItemPedidoRepository;
import com.edu.cursomc.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cursomc.domain.Pedido;
import com.edu.cursomc.repositories.PedidoRepository;
import com.edu.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BoletoService boletoService;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> 
						new ObjectNotFoundException("Objeto não encontrado Id: " + id 
								+ ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional()
    public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());

		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto){
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());

		for (ItemPedido umItem : obj.getItens()) {
			umItem.setDesconto(0.0);
			umItem.setPreco(produtoService.find(umItem.getProduto().getId()).getPreco());
			umItem.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
    }
}
