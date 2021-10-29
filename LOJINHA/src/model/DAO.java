package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conexão com o banco de dados
 * 
 * @author Djeniffer Vidal
 * @version 1.0
 */
public class DAO {

	// Paramentros de conexão

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.45.114:3306/dbloja";
	private String user = "djeniffe";
	private String password = "123@Senac";

	/**
	 * Metodo responsavel pela conexão com o banco de dados
	 * 
	 * @return con;
	 */
	public Connection conectar() {
		// A linha abaixo cria o objeto de nome 'con'
		Connection con = null;
		// Tratamento de exceções
		try {
			// As duas linhas abaixo estabelece a conexão
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
