/*
 * 18-july-2020
 * SignUp
 */

package com.todo.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.todo.DBServices.DBServices;
import com.todo.bean.User;

	/**
	 * This class deals with New User Signup Process.
	 */
public class SignUp extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * This method add's new user in database.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						  throws ServletException, IOException {
		String name  = request.getParameter("name").trim();
		String email  = request.getParameter("email").trim();
		String password  = request.getParameter("password").trim();
		try {
			
			/* checking if email already exist or not in databases */
			if(DBServices.checkEmail(email)) {
				request.setAttribute("mes", "email already exists.");
				request.getRequestDispatcher("SignUp.jsp").forward(request , response); 
				}else {
					
					/* ceating new user with signup From data */
					User user = new User(name,email,password);
					
					/* adding new user in databse*/
					DBServices.addUser(user);
					response.sendRedirect("Login.jsp");
				}
			}
			catch (ClassNotFoundException | SQLException e) {
				errorMessage(request, response);
			}	
	}
	
	/**
	 * In case an error occurs, this method forwards the request
	 * to errorPage.jsp with a message and a link as attributes.
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request,
	 * 						  HttpServletResponse response)
	 */
	public static void errorMessage(HttpServletRequest request,HttpServletResponse response)
									throws ServletException, IOException {
		request.setAttribute("message", "Sorry an Error Has Occured");
		request.setAttribute("link", "SignUp.jsp");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}

}
