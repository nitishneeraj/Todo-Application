/*
 * DBServices
 * 16-july-2020
 */

package com.todo.DBServices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.todo.DBSource.DBSource;
import com.todo.bean.Todo;
import com.todo.bean.User;

	/**
	 *This class provides database  services.
	 */
public class DBServices {
	
	/**
	 * This method checks if email is already present in database
	 * or not. 
	 * 
	 * @param email an email in the form of String.
	 * @return true if email is present in database already.
	 * @throws ClassNotFoundException if mysql-connector jar is not present.
	 * @throws SQLException if unable to connect to database. 
	 * @see com.todo.DBSource.DBSource
	 * @see java.sql.PreparedStatement
	 */
	public static boolean checkEmail(String email) throws ClassNotFoundException, SQLException {
		boolean status = false;
		Connection conn = DBSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from user_details where email = ?");
		stmt.setString(1, email);
		ResultSet rst = stmt.executeQuery();
		status= rst.next();
		DBSource.closeConnection(conn);
		return status;
	}
	
	/**
	 * This method adds a new user in tha database.
	 * 
	 * @param user a User class object. 
	 * @throws ClassNotFoundException if mysql-connector jar is not present.
	 * @throws SQLException if unable to connect to database.
	 * @see com.todo.DBSource.DBSource
	 * @see java.sql.PreparedStatement
	 */
	public static void addUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = DBSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into user_details (name,email,password) values(?,?,?)");
		stmt.setString(1,user.getName());
		stmt.setString(2, user.getEmail());
		stmt.setString(3, user.getPassword());
		stmt.executeUpdate();
		DBSource.closeConnection(conn);
	}
	
	/**
	 * This method fetches user details from database corresponding to
	 * email and password parameters.
	 *  
	 * @param email an email in the form of String.
	 * @param password a password as String.
	 * @return a User class Object with details.
	 * @throws ClassNotFoundException if mysql-connector jar is not present.
	 * @throws SQLException if unable to connect to database.
	 * @see com.todo.DBSource.DBSource
	 * @see java.sql.PreparedStatement
	 * @see com.todo.bean.User
	 */
	public static User getUserDetails(String email,String password) throws SQLException, ClassNotFoundException {
		Connection conn = DBSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from user_details where email = ? and password = ?");
		stmt.setString(1, email);
		stmt.setString(2, password);
		ResultSet rst = stmt.executeQuery();
		User user = null;
		if(rst.next()) {
		user = new User();
		user.setUserId(rst.getInt("user_id"));
		user.setName(rst.getString("name"));
		}
		DBSource.closeConnection(conn);
		return user;
	}
	
	/**
	 * This method get list of all todos present in tha database corresponding
	 * to the userId parameter.
	 * 
	 * @param userId an integer id which represents a User class object. 
	 * @return a List of type Todo containing all todos present.
	 * @throws ClassNotFoundException if mysql-connector jar is not present.
	 * @throws SQLException if unable to connect to database.
	 * @see com.todo.DBSource.DBSource
	 * @see java.sql.PreparedStatement
	 * @see java.util.List
	 * @see java.util.ArrayList
	 */
	public static List<Todo> getTodoList(int userId) throws ClassNotFoundException, SQLException {
		List <Todo> todos = new ArrayList<Todo>();
		Connection conn = DBSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from todo_details where user_id = ?");
		stmt.setInt(1, userId);
		ResultSet rst = stmt.executeQuery();
		while(rst.next()) {
			Todo t = new Todo();
			t.setTodoId(rst.getInt("todo_id"));
			t.setSubject(rst.getString("subject"));
			t.setDescription(rst.getString("description"));
			t.setDate(rst.getDate("date").toString());
			todos.add(t);
		}
		DBSource.closeConnection(conn);
		return todos;
	}
	
	/**
	 * This method add a new todo to database corresponding to 
	 * a userId.
	 * 
	 * @param todo
	 * @throws ClassNotFoundException if mysql-connector jar is not present.
	 * @throws SQLException if unable to connect to database.
	 * @see com.todo.DBSource.DBSource
	 * @see java.sql.PreparedStatement
	 */
	public static void addTodo(Todo todo) throws ClassNotFoundException, SQLException {
		Connection conn = DBSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into todo_details (user_id,subject,description,date) values(?,?,?,?)");
		stmt.setInt(1,todo.getUserId());
		stmt.setString(2, todo.getSubject());
		stmt.setString(3, todo.getDescription());
		stmt.setDate(4,Date.valueOf(todo.getDate()));
		stmt.executeUpdate();
		DBSource.closeConnection(conn);
	}
	
	/**
	 * This method deletes a todo from the database correspondig
	 * to the todoId passed as parameter.
	 * 
	 * @param todoId an Integer id corresponding to a todo.
	 * @throws ClassNotFoundException if mysql-connector jar is not present.
	 * @throws SQLException if unable to connect to database.
	 * @see com.todo.DBSource.DBSource
	 * @see java.sql.PreparedStatement
	 */
	public static void deleteTodo(int todoId) throws ClassNotFoundException, SQLException {
		Connection conn = DBSource.getConnection();
		PreparedStatement stmt = conn.prepareStatement("delete from todo_details where todo_id = ?");
		stmt.setInt(1,todoId);
		stmt.executeUpdate();
		DBSource.closeConnection(conn);
	}
	
}
