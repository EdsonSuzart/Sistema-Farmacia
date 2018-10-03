//ESSE ARQUIVO BEAN EM JAVA É UTILIZADO PARA FAZER A COMUNICAÇÃO COM OS ARQUIVOS HTML

package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores") //nome que vai ser usada para puxar e fazer a interligação dos arquivos java com html
@ViewScoped
public class FornecedoresBean {

	private ArrayList<Fornecedores>itens;
	private ArrayList<Fornecedores>itensFiltrados;
	private Fornecedores fornecedores; // variavel que poderá acessar a classe Fornecedores externamente e pegar os seus dados

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	
	@PostConstruct
	public void prepararPesquisa() {
		
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = fdao.listar();
			
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	
	public void prepararNovo(){ //Método que vai preparar meu botão novo, ele age como se fosse preparar uma linha nova 
		fornecedores = new Fornecedores();
	}
	
	public void novo() {
		
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.salvar(fornecedores);
			//Atualiza na lista quando eu salvar novo dado
			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso! "); // puxa minha classe JSFUtil e mostra qual a mensagem de erro 
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	

	
	public void excluir() {
		
		
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.excluir(fornecedores);
			
			
			itens = fdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Fornecedor deletado com sucesso! "); // puxa minha classe JSFUtil e mostra qual a mensagem de erro 
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possível excluir um fornecedor que tenha um produto associado.");
			e.printStackTrace();
		}
	}
	
	
	
	public void editar() {
		
		
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.editar(fornecedores);
			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso! "); // puxa minha classe JSFUtil e mostra qual a mensagem de erro 
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
}
