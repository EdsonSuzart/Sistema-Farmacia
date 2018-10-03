package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProdutos") // nome que vai ser usada para puxar e fazer a interligação dos arquivos java
									// com html
@ViewScoped
public class ProdutoBean {

	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	private Produtos produtos; // variavel que poderá acessar a classe Produtos externamente e pegar os seus
								// dados
	private ArrayList<Fornecedores> comboFornecedores;


	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutoDAO pdao = new ProdutoDAO();
			itens = pdao.listar();

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {

		try {
			produtos = new Produtos();
			FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Aqui ele irá atribuir o meu combo box, o método para listar todos os
			// fornecedores
	}

	public void novo() {

		try {
			
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.salvar(produtos);
			// Atualiza na lista quando eu salvar novo dado
			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso! "); // puxa minha classe JSFUtil e mostra
																				// qual a mensagem de erro
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void excluir() {

		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.excluir(produtos);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Produto deletado com sucesso! "); // puxa minha classe JSFUtil e mostra
																				// qual a mensagem de erro
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void editar() {

		try {
			ProdutoDAO fdao = new ProdutoDAO();
			fdao.editar(produtos);
			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso! "); // puxa minha classe JSFUtil e mostra
																					// qual a mensagem de erro
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	
	public void prepararEditar() {
		produtos = new Produtos();
		try {
			
			FornecedoresDAO dao = new FornecedoresDAO();
			comboFornecedores = dao.listar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Aqui ele irá atribuir o meu combo box, o método para listar todos os
			// fornecedores
	}

}
