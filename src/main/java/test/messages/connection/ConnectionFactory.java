package test.messages.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author bruno ferrari
 * @since 06-28-2015
 * Class for mysql database connection
 */

public class ConnectionFactory {
	
	private static final String URL = "jdbc:mysql://localhost:3306/message_test";
	private static final String USER = "root";
	private static final String PASS = "root";

	public static Connection connect() throws SQLException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
