<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List,com.todo.bean.Todo" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="css/style.css" />
  </head>
  <body>
  <% 
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if(session.getAttribute("name") == null){
		response.sendRedirect("Login.jsp");
	}
  List<Todo> list = (List)request.getAttribute("todos"); %>
  
    <nav>
      <div class="logo">
          <a href="UserHome.jsp"><img src="images/logo.png" /></a>
        <h2>TODO</h2>
      </div>
      
    </nav>
    <section class="main-container">
       <div class="todobtn">
           <a href="AddTodo.jsp">Add-Todo</a>
           <a href="logout">logout</a>
       </div>
       <div class="table-container">
        <table class="table"> 
            <thead><tr>
                <td>SUBJECT</td>
                <td>DESCRIPTION</td>
                <td>DATE</td>
                
                <td>DELETE</td>
                </tr>
            </thead>
            <tbody>
            <%for(Todo t : list){ %>
            <tr>
                <td><%= t.getSubject() %></td>
                <td><%= t.getDescription()%></td>
                <td><%= t.getDate() %></td>
                <td>
                	<form action="delete" method="post">
                		<input type="hidden" name="todoId" value="<%= t.getTodoId() %>">
                		<input type="submit" value="delete">
                	
                	</form>
                </td>
                </tr>
                
                <%} %>
            </tbody>
        </table>
       </div>
    </section>
  </body>
</html>
