package com.edu.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edu.cursomc.domain.Categoria;
import com.edu.cursomc.domain.Cidade;
import com.edu.cursomc.domain.Cliente;
import com.edu.cursomc.domain.Endereco;
import com.edu.cursomc.domain.Estado;
import com.edu.cursomc.domain.ItemPedido;
import com.edu.cursomc.domain.Pagamento;
import com.edu.cursomc.domain.PagamentoComBoleto;
import com.edu.cursomc.domain.PagamentoComCartao;
import com.edu.cursomc.domain.Pedido;
import com.edu.cursomc.domain.Produto;
import com.edu.cursomc.domain.enums.EstadoPagamento;
import com.edu.cursomc.domain.enums.TipoCliente;
import com.edu.cursomc.repositories.CategoriaRepository;
import com.edu.cursomc.repositories.CidadeRepository;
import com.edu.cursomc.repositories.ClienteRepository;
import com.edu.cursomc.repositories.EnderecoRepository;
import com.edu.cursomc.repositories.EstadoRepository;
import com.edu.cursomc.repositories.ItemPedidoRepository;
import com.edu.cursomc.repositories.PagamentoRepository;
import com.edu.cursomc.repositories.PedidoRepository;
import com.edu.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
