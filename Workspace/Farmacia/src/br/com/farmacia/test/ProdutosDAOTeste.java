package br.com.farmacia.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.DAO.ProdutoDAO;

public class ProdutosDAOTeste {

	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produtos p1 = new Produtos();

		p1.setDescricao("Rilan");
		p1.setPreco(1.00);
		p1.setQuantidade(10);

		Fornecedores f = new Fornecedores();
		f.setCodigo(23); // chamo a minha classe fornecedores, acho pelo c�digo e
		p1.setFornecedores(f);

		ProdutoDAO pdao = new ProdutoDAO();
		pdao.salvar(p1);

		System.out.println("Salvo com sucesso!");

	}

	@Test
	//@Ignore
	public void listar() throws SQLException {

		ProdutoDAO pdao = new ProdutoDAO();
		ArrayList<Produtos> lista = pdao.listar();// ler da seguinte forma: pego meu vetor de produtos e digo que ele
													// vai chamar o metodo listar DAO
		for (Produtos p : lista) {
			System.out.println("C�digo do produto " + p.getCodigo());
			System.out.println("Descri��o do produto " + p.getDescricao());
			System.out.println("Valor do produtos " + p.getPreco());
			System.out.println("Quantidade do produto " + p.getQuantidade());
			
			System.out.println("C�digo do fornecedor " + p.getFornecedores().getCodigo());
			System.out.println("Descri��o do fornecedor " + p.getFornecedores().getDescricao());
			System.out.println("");
		}
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException {
		
		Produtos p = new Produtos();
		p.setCodigo(5L);
		
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.excluir(p);
		
		System.out.println("Excluido com sucesso! ");
		
	}
	
	@Test
	@Ignore
	public void editar() throws SQLException {
		
		Produtos p = new Produtos();
		p.setCodigo(12L);
		p.setDescricao("Teste de edi��o");
		p.setPreco(56.99);
		p.setQuantidade(4);
		
		Fornecedores f = new Fornecedores();
		f.setCodigo(13);
		p.setFornecedores(f);
		
		ProdutoDAO pdao = new ProdutoDAO();
		pdao.editar(p);
		
		System.out.println("Editado com sucesso! ");
	}
	
	
}
