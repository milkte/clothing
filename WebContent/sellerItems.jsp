
<%@page import="com.model.User"%>
<%@page import="com.dao.AuthDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
        <script src="scripts/jquery-2.1.3.min.js"></script> 
    </head>
    <body>
        <div class="col-md-10 col-lg-10">
            <h1 style="color:darkgray; " class="col-md-12 col-lg-12" >Items</h1>
        </div>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg == null) {
                msg = "";
            }
            String userid = ((Integer) session.getAttribute("userid")).toString();
//            out.println("usrid: " + userid);
            String loggedinRole = (String) session.getAttribute("role");
            //            out.println("role: " + loggedinRole);
            if (loggedinRole == null) {
                loggedinRole = "";
            } else if (loggedinRole.equals(AuthDAO.ADMIN)) {
                List<User> users = AuthDAO.retrieveUserByRole(AuthDAO.SELLER_ROLE);
                //                 out.print(users);
                if (users != null) {
        %>
        <select class="form-control" id="seller1">
            <%
                for (User user : users) {
            %>
            <option value="<%=user.getUserId()%>"><%=user.getFirstName() + " " + user.getLastName()%></option>
            <%
                }%>
        </select>
        <%
            }
        } else if (loggedinRole.equals(AuthDAO.SELLER_ROLE)) {
        %>
        <select class="form-control" style="visibility: hidden" id="seller">
            <option value="<%=userid%>" selected></option>
        </select>
        <%
            }%>
        <div id="items" class="col-xs-12 col-md-10 col-lg-10">
            <div class="row">
                <div class="col-xs-3 col-md-3 col-lg-3  control-label" >Name</div><div class="col-xs-3 col-md-3 col-lg-3  control-label" >Price</div><div class="col-xs-3 col-md-3 col-lg-3 control-label" >Style</div>
            </div>
        </div>

        <script>
            function fillItems(selectedValue) {
                $.get("SellerItemsServlet", {'seller': selectedValue}, function(data) {
//                  var object = JSON.parse(data);
//                  alert(object + "  ");

                    $.each(data, function(key, value) {

//            console.log("created");
                        var divSubmit = $(document.createElement('div'));
                        divSubmit.attr("class", "row");
                        $(divSubmit).append('<div class="col-xs-3 col-md-3 col-lg-3" >' + value.itemName + '</div><div class="col-xs-3 col-md-3 col-lg-3" >' + value.price + '</div><div class="col-xs-3 col-md-3 col-lg-3" >' + value.style.name + '</div>');
                        $("#items").append(divSubmit);
                    });

                });
            }
            $(document).ready(function() {
            	
//                alert("ready");
					$('#body').css('left',"100px");
                $.get("SellerItemsServlet", {'get': 'seller'}, function(data) {
                    var $select = $('#seller1');
                    $select.find('option').remove();
//                  var object = JSON.parse(data);
//                  alert(object + "  ");
                    $.each(data, function(key, value) {
                        $('<option>').val(value.userId).text(value.firstName + " " + value.lastName).appendTo($select);
                    });
                });

                var selectedValueseller = $("#seller").find(":selected").val();
                var selectedValueadmin = $("#seller1").find(":selected").val();
                if (selectedValueadmin) {
//     alert("in admin")
                    fillItems(selectedValueadmin);
                }
// alert(selectedValueadmin + "  " + selectedValueseller)
                if (selectedValueseller) {
//     alert("in seller");
                    fillItems(selectedValueseller);
                }
                $("#seller1").change(function(data) {
                    var selectedValue = $("#seller1").find(":selected").val();
                    fillItems(selectedValue);

                });
            });
        </script>
    </body>
</html>