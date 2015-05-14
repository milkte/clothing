<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Logout Page</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
        
    </head>

    <body>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg == null) {
                msg = "";
            }
            String loggedIn = (String) request.getAttribute("loggedIn");
            if (loggedIn == null) {
                loggedIn = "";
            }
        %>
        <h1 style="color:green">Logout</h1></hr>
        <p><%= msg%></p>
        <%
            if (loggedIn.equalsIgnoreCase("true")) {
        %><form action='LogoutServlet' method='post'>
            <input cl type='submit' value='Logout' />
        </form>

        <%}%>
        %>			
    </body>
</html>
