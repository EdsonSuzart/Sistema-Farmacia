package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.Conexao;

public class ProdutoDAO {

		public void salvar(Produtos p) throws SQLException {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO produtos ");
			sql.append("(descricao, preco, quantidade, fornecedores_codigo) ");
			sql.append("VALUES (?,?,?,?) ");

			Connection conex = Conexao.conectar();

			PreparedStatement comando = conex.prepareStatement(sql.toString());
			
			comando.setString(1, p.getDescricao());
			comando.setDouble(2, p.getPreco());
			comando.setInt(3, p.getQuantidade());
			comando.setLong(4, p.getFornecedores().getCodigo());
			comando.executeUpdate();
		}
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		public ArrayList<Produtos> listar() throws SQLException {

			StringBuilder sql = new StringBuilder();//
			sql.append("SELECT p.codigo, p.descricao, p.quantidade, p.preco, f.codigo, f.descricao ");// variaveis que sao de produtos ficam com p e as que serão puxadas da classe fornecedores terao prefixo f
			sql.append("FROM produtos AS p ");
			sql.append("INNER JOIN fornecedores AS f ON f.codigo = p.fornecedores_codigo ");// ele vai buscar quando o codigo dos fornecedores tambem existir na tabela de produtos e listar todos os produtos que estao interligados

			Connection conex = Conexao.conectar();// Traz a classe conectar e faz a conexão com o bd

			PreparedStatement comando = conex.prepareStatement(sql.toString());

			ResultSet resultado = comando.executeQuery();// traz o resultado que estiver
			
			ArrayList<Produtos> lista = new ArrayList<Produtos>();// instancia uma lista de produtos

			while (resultado.next()) {
				
				Fornecedores f = new Fornecedores();
				
				f.setCodigo(resultado.getInt("f.codigo"));
				f.setDescricao(resultado.getString("f.descricao"));
				
				Produtos p = new Produtos();
				
				p.setCodigo(resultado.getLong("p.codigo"));
				p.setDescricao(resultado.getString("p.descricao"));
				p.setQuantidade(resultado.getInt("p.quantidade"));
				p.setPreco(resultado.getDouble("p.preco"));
				p.setFornecedores(f);
				
				
				lista.add(p);

			}
			return lista;

		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		public void excluir(Produtos p) throws SQLException {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM produtos ");
			sql.append("WHERE codigo = ? ");

			Connection conex = Conexao.conectar();

			PreparedStatement comando = conex.prepareStatement(sql.toString());
			comando.setLong(1, p.getCodigo());
			comando.executeUpdate();

		}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
				
		public void editar(Produtos p) throws SQLException {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE produtos ");
			sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
			sql.append("WHERE codigo = ? ");

			Connection conex = Conexao.conectar();

			PreparedStatement comando = conex.prepareStatement(sql.toString());
			comando.setString(1, p.getDescricao());
			comando.setInt(2, p.getQuantidade());
			comando.setDouble(3, p.getPreco());
			comando.setInt(4, p.getFornecedores().getCodigo());
			comando.setLong(5, p.getCodigo());
			comando.executeUpdate();

		}

}
