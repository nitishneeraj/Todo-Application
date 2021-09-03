<%@ page language="java" contentType="text/html; charset=ISO-8859-1 " isErrorPage="true"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
      body {
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        overflow: hidden;
        background-color: blanchedalmond;
      }

      .main-Container {
        width: 300px;
        height: 200px;
        border: 2px solid black;
        background-color: white;
        border-radius: 10px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        display: flex;
        flex-direction: column;
        justify-content: space-evenly;
        align-items: center;
        text-transform: uppercase;
      }
      .btn {
        padding: 15px 40px;
        outline: none;
        border-radius: 10px;
      }
      
    </style>
  </head>
  <body>
    <header class="main-Container">
      <div class="message">
        <%= request.getAttribute("message") %>
      </div>

      <div class="container">
        <a class="txt1" href="<% if( request.getAttribute("link")!=null){out.println(request.getAttribute("link"));}else{out.println("Login.jsp");} %>">
          <button class="btn" type="button">Retry</button>
        </a>
      </div>
    </header>
  </body>
</html>
