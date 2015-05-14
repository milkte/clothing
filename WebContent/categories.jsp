
<%@page import="com.model.Item"%>
<%@page import="com.model.Size"%>
<%@page import="com.model.Color"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Categories</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
        <script src="scripts/jquery-2.1.3.min.js"></script>
    </head>
    <body>
        <%

            String msg = (String) request.getAttribute("msg");
            if (msg == null) {
                msg = "";
            }
            String loggedIn = (String) session.getAttribute("loggedIn");

            List<Category> categories = null;
            categories = (List<Category>) application.getAttribute("categories");
//            
//                for (Category category : categories) {

//            }
 			List<Item> items = null;
            Object catItems =  request.getAttribute("items");
           
                
            Object category = request.getAttribute("categories");
            if (category != null && category instanceof List) {
                categories = (List<Category>) category;
        %>
        <br>
        <br>
        <table class="table table-hover">
            <%
                for (Category category1 : categories) {

            %>
            <tr>
                <td>
                    <a target="body" href="SearchServlet?category=<%=category1.getId()%>" class="btn btn-lg">
                        <%=category1.getCategoryName()%></a>
                </td>
            </tr>
            <%
                }
                List<Color> colors = (List<Color>) application.getAttribute("color");
                List<Size> sizes = (List<Size>) application.getAttribute("size");
            %>
            <div class="col-xs-12 col-md-12 col-lg-12">
                <h4>Color</h4>
                
                <%
                    for (Color color : colors) {
                %>
                <div class="row">
                <input type="checkbox"  name="color" value="<%=color.getId()%>"><%=color.getName()%>
                </div>
                <%
                    }
                    out.println(" <h4>Size</h4>");
                    for (Size size : sizes) {
                %>
                <div class="row">
                <input type="checkbox"  name="size" value="<%=size.getId()%>"><%=size.getSizeCode()%>
                </div>
                <%
                        }
                %>
            </div>
            <%
                } else {

                }
            %>
        </table>
        <%
        if (catItems != null && catItems instanceof List) {
        	items = (List<Item>) catItems;
        	 
              for (Item item : items) {
        %>
        <p><%= msg%></p>
        
            <div class="row">
                <div class="col-xs-6  col-md-4 col-lg-4">
 	           <a target="body" href="SearchItemServlet?item=<%=item.getItemid()%>&iscat=true" class="btn btn-lg">
                        <%=item.getItemName()%></a>
                </div>
                
            </div>
            <%
                    }
                } else {

                }%>
             
     
    </body>
</html>
