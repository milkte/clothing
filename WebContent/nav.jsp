<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html>

    <head>

    </head>
    <body>
        <h1 style="color:green">Navigation page</h1></hr>
        <a href="<%=getServletContext().getContextPath() %>/index.jsp">Home</a>
        <a href="<%=getServletContext().getContextPath() %>/login.jsp">Login</a>
        <a href="<%=getServletContext().getContextPath() %>/signup.jsp">Sign Up</a>
    </body>
</html>
