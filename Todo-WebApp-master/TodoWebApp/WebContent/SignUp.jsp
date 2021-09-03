<%@ page language="java" contentType="text/html; charset=ISO-8859-1" errorPage="errorPage.jsp"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>SignUp</title>

    <link rel="stylesheet" href="css/signup.css" />
  </head>
  <body>
    <header class="main-container">
      <div class="form-container">
      
        <!--                          email not exists condition check                           -->        
            <%if(request.getAttribute("mes2")!=null){
            	out.print(request.getAttribute("mes2"));
            	out.print("<br>");
            }
             %>
      
        <form action="signup" method="post">
          <div class="row">
            <label>Name</label><br />
            <input type="text" name="name" required="required"  />
          </div>

          <div class="row">
            <label>Email</label><br />
    <!--                          email null condition check                           -->        
            <%if(request.getAttribute("mes")!=null){
            	out.print(request.getAttribute("mes"));
            	out.print("<br>");
            }
             %>
            <input type="email" name="email" required="required" />
          </div>

          <div class="row">
            <label>Password</label><br />
            <input type="password" name="password"  required="required"/>
          </div>

          <div class="row btn">
            <input type="submit" value="SignUp" />
          </div>
        </form>
      </div>
    </header>
  </body>
</html>
