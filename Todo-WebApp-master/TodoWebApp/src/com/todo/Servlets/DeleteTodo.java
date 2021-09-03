/*
 * 18-july-2020
 * DeleteTodo
 */

package com.todo.Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.todo.DBServices.DBServices;
import com.todo.bean.Todo;

	/**
 	 * This class deals with delete todo function.
 	 */
public class DeleteTodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	/**
	 * This method delete a todo from database.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			              throws ServletException, IOException {
		
		  int todoId = Integer.parseInt(request.getParameter("todoId").trim());
		  try {
			  
			  /*passing totoId as parameter top delete todo */
           DBServices.deleteTodo(todoId);
           HttpSession session = request.getSession();
           
           /*refetching updated todo list after deletion */ 
           List<Todo> todos = DBServices.getTodoList((int)session.getAttribute("userId"));
           request.setAttribute("todos", todos);
           
           /*sending request back to userhome page with updated todo*/
           request.getRequestDispatcher("UserHome.jsp").forward(request, response);
		   }catch (Exception e) {
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
	public static void errorMessage(HttpServletRequest request, HttpServletResponse response)
			                        throws ServletException, IOException {
		request.setAttribute("message", "Sorry an Error Has Occured");
		request.setAttribute("link", "UserHome.jsp");
		request.getRequestDispatcher("errorPage.jsp").forward(request, response);
	}

}
