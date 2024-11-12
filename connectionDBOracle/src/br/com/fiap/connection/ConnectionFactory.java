package br.com.fiap.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection conectar() {
		try {
		
			return DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "tm06", "11052007");
		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar ao banco de dados", e);
		}

	}

	public static Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

}
