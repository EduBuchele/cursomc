package com.edu.cursomc.services;

import com.edu.cursomc.domain.*;
import com.edu.cursomc.domain.enums.EstadoPagamento;
import com.edu.cursomc.domain.enums.TipoCliente;
import com.edu.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaDAO;
    @Autowired
    private ProdutoRepository produtoDAO;
    @Autowired
    private EstadoRepository estadoDAO;
    @Autowired
    private CidadeRepository cidadeDAO;
    @Autowired
    private ClienteRepository clienteDAO;
    @Autowired
    private EnderecoRepository enderecoDAO;
    @Autowired
    private PedidoRepository pedidoDAO;
    @Autowired
    private PagamentoRepository pagamentoDAO;
    @Autowired
    private ItemPedidoRepository itemPedidoDAO;

    public void instantiateTestDatabase() throws ParseException {

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Categoria03");
        Categoria cat4 = new Categoria(null, "Categoria04");
        Categoria cat5 = new Categoria(null, "Categoria05");
        Categoria cat6 = new Categoria(null, "Categoria06");
        Categoria cat7 = new Categoria(null, "Categoria07");

        Produto p1 = new Produto(null, "Computador", 2000.00 );
        Produto p2 = new Produto(null, "Impressora", 800.00 );
        Produto p3 = new Produto(null, "Mouse", 80.00 );
        Produto p4 = new Produto(null, "Mesa de escritorio", 300.00 );
        Produto p5 = new Produto(null, "Toalha", 50.00 );
        Produto p6 = new Produto(null, "Colcha", 200.00 );
        Produto p7 = new Produto(null, "TV true color", 1200.00 );
        Produto p8 = new Produto(null, "Roçadeira", 800.00 );
        Produto p9 = new Produto(null, "Abajour", 100.00 );
        Produto p10 = new Produto(null, "Pendente", 180.00 );
        Produto p11 = new Produto(null, "Shampoo", 90.00 );

        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
        cat2.getProdutos().addAll(Arrays.asList(p2,p4));
        cat3.getProdutos().addAll(Arrays.asList(p5,p6));
        cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
        cat5.getProdutos().addAll(Arrays.asList(p8));
        cat6.getProdutos().addAll(Arrays.asList(p9,p10));
        cat7.getProdutos().addAll(Arrays.asList(p11));

        p1.getCategorias().addAll(Arrays.asList(cat1));
        p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaDAO.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoDAO.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        estadoDAO.saveAll(Arrays.asList(est1, est2));
        cidadeDAO.saveAll(Arrays.asList(c1, c2, c3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "maira@gmail.com", "27361414079", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("(63) 2688-9777", "(63) 99942-2824"));

        Endereco e1 = new Endereco(null, "Rua flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

        cli1.getEndereco().addAll(Arrays.asList(e1, e2));
        clienteDAO.saveAll(Arrays.asList(cli1));
        enderecoDAO.saveAll(Arrays.asList(e1,e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pagto2);
        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoDAO.saveAll(Arrays.asList(ped1, ped2));
        pagamentoDAO.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));

        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));

        itemPedidoDAO.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}
