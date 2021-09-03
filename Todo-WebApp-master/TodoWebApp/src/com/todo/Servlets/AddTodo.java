/*
 * AddTodo
 * july-18-2020
 */

package com.todo.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todo.DBServices.DBServices;
import com.todo.bean.Todo;

public class AddTodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
      
	/**
	 * This method handled Http<POST> request.It takes parameter from request and 
	 * passes them to database to addTodo method.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						  throws ServletException, IOException {
		String subject = request.getParameter("subject").trim(); 
		String description = request.getParameter("description").trim(); 
		String date = request.getParameter("date").toString().trim();
		
		/*getting session object*/
		HttpSession session = request.getSession();
		
		/* getting userId from session object */
		int userId = (int)session.getAttribute("userId");
		
		/* creating new Todo object with request data */
		Todo todo = new Todo();
		todo.setSubject(subject);
		todo.setDescription(description);
		todo.setUserId(userId);
		todo.setDate(date);
		
		try {
			
			/*adding Todo in database*/
			DBServices.addTodo(todo);
			
			/*refetching updated todo-List from databases after addition*/ 
			List<Todo> todos = DBServices.getTodoList((int)session.getAttribute("userId"));
			request.setAttribute("todos", todos);
			
			/*forwarding request back to UserHome page with updated todo*/
			request.getRequestDispatcher("UserHome.jsp").forward(request, response);
			
		}catch(Exception e) {
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
		request.setAttribute("link", "UserHome.jsp");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}

}
