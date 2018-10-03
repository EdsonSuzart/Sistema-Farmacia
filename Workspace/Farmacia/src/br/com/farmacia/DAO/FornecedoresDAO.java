//ESSA CLASSE DAO É ONDE FICARÁ TODOS OS MEUS MÉTODOS PRINCIPAIS DE EXECUÇÃO DE TAREFAS, COMO: SALVAR, EXCLUIR, LISTAR, BUSCAR...

package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.Conexao;

public class FornecedoresDAO {

	public void salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");

		Connection conex = Conexao.conectar();

		PreparedStatement comando = conex.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	////////////////////////////////////////////////////////////////////////////////////
	public void editar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conex = Conexao.conectar();

		PreparedStatement comando = conex.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setInt(2, f.getCodigo());
		comando.executeUpdate();

	}

	////////////////////////////////////////////////////////////////////////////////////
	public void excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conex = Conexao.conectar();

		PreparedStatement comando = conex.prepareStatement(sql.toString());
		comando.setInt(1, f.getCodigo());
		comando.executeUpdate();

	}

	////////////////////////////////////////////////////////////////////////////////////
	public Fornecedores buscarPorCodigo(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conex = Conexao.conectar();

		PreparedStatement comando = conex.prepareStatement(sql.toString());

		comando.setInt(1, f.getCodigo());
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	////////////////////////////////////////////////////////////////////////////////////
	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conex = Conexao.conectar();

		PreparedStatement comando = conex.prepareStatement(sql.toString());

		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while (resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getInt("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);

		}
		return lista;

	}

	////////////////////////////////////////////////////////////////////////////////////
	public ArrayList<Fornecedores> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();//
		sql.append("SELECT codigo, descricao ");// utiliza codigos sql para indicar o que deve ser buscado
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");

		Connection conex = Conexao.conectar();// Traz a classe conectar e faz a conexão com o bd

		PreparedStatement comando = conex.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();// traz o resultado que estiver
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();// instancia uma lista de fornecedores

		while (resultado.next()) {//
			Fornecedores f = new Fornecedores();// instanciando um objeto que irá trazer como resultado os dados do
												// banco de dados
			f.setCodigo(resultado.getInt("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);

		}
		return lista;

	}

	////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		/*
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(7);

		FornecedoresDAO fdao = new FornecedoresDAO();

		try {
			fdao.excluir(f1);
			System.out.println("Excluido com sucesso! ");

		} catch (SQLException e) {
			System.out.println("Erro ao excluir! ");
			e.printStackTrace();
		}

		
		 * 
		 * 
		 
		 * 
		 * FornecedoresDAO fdao = new FornecedoresDAO();
		 * 
		 * Fornecedores f1 = new Fornecedores(); f1.setCodigo(1);
		 * 
		 * try { fdao.buscarPorCodigo(f1);
		 * 
		 * System.out.println("Resultado:" + f1);
		 * 
		 * } catch (SQLException e) { System.out.println("Erro ao Buscar! ");
		 * e.printStackTrace(); }
		 * 
		 * 
		 * FornecedoresDAO fdao = new FornecedoresDAO();
		 * 
		 * try { ArrayList<Fornecedores> lista = fdao.listar();
		 * 
		 * for (Fornecedores f : lista) { System.out.println("Resultado " + f); }
		 * 
		 * } catch (SQLException e) { System.out.println("Erro ao Buscar! ");
		 * e.printStackTrace(); }
		 * 
		 * 
		 * Fornecedores f1 = new Fornecedores(); f1.setDescricao("L");
		 * 
		 * FornecedoresDAO fdao = new FornecedoresDAO(); try {
		 * ArrayList<Fornecedores>lista = fdao.buscarPorDescricao(f1);
		 * 
		 * for (Fornecedores f : lista) { System.out.println("Resultado " + f); }
		 * 
		 * } catch (SQLException e) { System.out.println("Erro ao Buscar! ");
		 * e.printStackTrace(); }
		 */
	}
}
