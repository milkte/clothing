

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String loggedIn = (String) session.getAttribute("loggedIn");
//            if (loggedIn == null) {
//                out.println("<h5 style='color:red'>Access denied. Login ti access this feature</h5>");
//            } else {
        %>
        <div class="col-md-10 col-md-offset-1">
            <form action="SendMessage" method="post">
                <div class="form-group">
                    <div class="col-md-2"><label class="control-label">Enter receiver</label></div>
                    <div class="col-md-6"><input type="text" name='receiver' class="form-control"></div>
                </div>
                <div class="form-group">
                    <div class="col-md-2"><label class="control-label">Enter message</label></div>
                    <div class="col-md-6"><textarea rows="5" cols="40" name='message' class="form-control"></textarea></div>
                </div>
                
                <div class="form-group">
                    <input type="submit" class="btn btn-success" >
                </div>
            </form>
        </div>
        <%
//            }
        %>
    </body>
</html>
