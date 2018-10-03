// ESSSA CLASSE É ONDE FAZ A CONEXAO COM MEU BANCO DE DADOS

package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/SistemaFarmacia";

	public static Connection conectar() throws SQLException {
		
		//Referencia ao driver mysql para versoes antigas do java
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA); 
		return conexao;
	}

	public static void main(String[] args) {
		try {
			Connection cone = Conexao.conectar();
			System.out.println("Conexão deu certo!!");
		}
		catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Conexão falhou!!");
		}
	}
}
