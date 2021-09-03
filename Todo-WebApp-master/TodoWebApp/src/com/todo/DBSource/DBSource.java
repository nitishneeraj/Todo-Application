/*
 * DBSource
 * 16-july-2020.
 */

package com.todo.DBSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
	 
	/**
	 * This class provides services regarding database connection.
	 */
public class DBSource {
	
	private static Connection CONN;
	
	/**
	 * This method creates a connection with database.
	 * 
	 * @return a Database Connection object.
	 * @throws ClassNotFoundException if mysql-connector jar is not present.
	 * @throws SQLException if unable to connect to database.
	 * @see java.lang.Class
	 * @see java.sql.DriverManager
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		CONN = DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", ""); 
		return CONN;
	} 
	
	/**
	 *This method closes the database connection.
	 *
	 * @param conn a connection object.
	 * @throws SQLException if unable to connect to database.
	 * @see java.sql.Connection
	 */
	public static void closeConnection(Connection conn) throws SQLException {
		CONN.close();
	}
}
