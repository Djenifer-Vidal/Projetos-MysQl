package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Conex�o com o banco de dados
 * 
 * @author Djeniffer Vidal
 * @version 1.0
 */
public class DAO {

	// Paramentros de conex�o

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://10.26.45.114:3306/dbloja";
	private String user = "djeniffe";
	private String password = "123@Senac";

	/**
	 * Metodo responsavel pela conex�o com o banco de dados
	 * 
	 * @return con;
	 */
	public Connection conectar() {
		// A linha abaixo cria o objeto de nome 'con'
		Connection con = null;
		// Tratamento de exce��es
		try {
			// As duas linhas abaixo estabelece a conex�o
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
