/*
 * Login
 * 16-july-2020
 */

package com.todo.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todo.DBServices.DBServices;
import com.todo.bean.Todo;
import com.todo.bean.User;

	/**
	 * This class(Servlet) deals with  request coming from Login.jsp page.
	 */
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * This method fetches two parameters(email, password) from request object 
	 * coming from Login.jsp page and checks if user exist in database or not.
	 * if user do not exist then it forwards the request to SignUp.jsp page.
	 * if user exist then a HttpSession object is created for the user with userId
	 * and name as attributes.Then it forwards the request to UserHome.jsp page
	 * with a List<Todo> as attribute.
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @see com.todo.DBServices.DBServices
	 * @see com.todo.bean.User
	 * @see java.util.List
	 * @see javax.servlet.http.HttpSession
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						  throws ServletException, IOException {
		final String email = request.getParameter("email").trim();
		final String password = request.getParameter("password").trim();
		try {
			User user = (User) DBServices.getUserDetails(email, password);
			if (user!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getUserId());
				session.setAttribute("name", user.getName());
				List<Todo> todos = DBServices.getTodoList((int)session.getAttribute("userId"));
				request.setAttribute("todos", todos);
				request.getRequestDispatcher("UserHome.jsp").forward(request, response);
				}else {
					request.setAttribute("mes2", "please Signup.");
					request.getRequestDispatcher("SignUp.jsp").forward(request , response); 
				}
			}
			catch (ClassNotFoundException | SQLException e) {
				errorMessage(request, response);
			}	
	}
		
	/**
	 * In case an error occurs, this method forwards the request to errorPage.jsp 
	 * with a message and a link as attributes.
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public static void errorMessage(HttpServletRequest request, HttpServletResponse response)
									throws ServletException, IOException {
		request.setAttribute("message", "Sorry an Error Has Occured");
		request.setAttribute("link", "Login.jsp");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}

}
