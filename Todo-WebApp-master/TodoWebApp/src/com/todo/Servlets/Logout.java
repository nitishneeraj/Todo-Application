/*
 * Login
 * 16-july-2020
 */
package com.todo.Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	/**
	 * This class(Servlet) helps in logging out a user. 
	 */
public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * This method removes the session attribute and invalidates 
	 * the session for a user.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request,HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
						   throws ServletException, IOException {
		
		HttpSession session = request.getSession(); 
		
		if (session != null) {
			session.removeAttribute("name");
			session.invalidate();
			response.sendRedirect("Login.jsp");
		}else {
			errorPage(request, response);
			return;
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
	public static void errorPage(HttpServletRequest request, HttpServletResponse response)
			   					 throws ServletException, IOException {
		request.setAttribute("message", "Something Went Wrong");
		request.setAttribute("link", "Login.jsp");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}

}
