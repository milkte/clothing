
<%@page import="java.util.LinkedList"%>
<%@page import="com.model.Style"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css" />
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
        <script src="scripts/jquery-2.1.3.min.js"></script>
    </head>
    <body>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg == null) {
                msg = "";
            }
            String loggedIn = (String) session.getAttribute("loggedIn");
        %>
        <h1 style="color: green">Add Category</h1>
        </hr>
        <p dir="rtl">
            <a href="index.jsp">Home</a> <a href="/LoginWeb/nav.jsp">Navigation</a>
            <%
                if (loggedIn == null) {
                    loggedIn = "";
            %>
            <a href="/Clothing/LoginServlet">Login</a>
        </p>
        <br>
        <%
            }
//	           List<Category> categories = (List<Category>) application.getAttribute("categories");
            //            if (categories != null) {
            //
            //                for (Category category : categories) {
%>

        <br>
        <br>
    <legend>Order</legend>
    <p><%=msg%></p>
    <!--<h5 style="color:red">Could not add category. Categoryname is null</h5>-->
    <form action="OrderServlet" method="post" class="form-horizontal">
        <fieldset>
            <input type="hidden" name="noOfStyles" id="noOfStyles" />
            <div class="table col-md-8 col-md-offset-1">
                <div class="row form-group">
                    <div class="col-md-2">
                        <label for="estimate" class="control-label">Estimated delivery date</label>
                    </div>
                    <div class="col-md-6">
                        <label id="estimate">3 working days</label>
                    </div>
                </div>
                <div class="  form-group">

                    <div class="col-md-2">
                        <label for="payment" class="control-label">Payment method:</label>
                    </div>
                    <div class="col-md-6">
                        <select class="form-control" name="payment" id="payment">
                            <option value="Credit card">Credit card</option>
                            <optoin value="Cash on Delivery">Cash on Delivery</optoin>
                        </select>
                    </div>
                </div>
                <div class="row"><h3>Address</h3></div>
                <div class="form-group">
                    <input type="radio" name="add" value="0" class="control-label" checked="true">Select existing address</label>
                    <input type="radio" name="add" value="1" class="control-label">Add other address</label>
                </div>
                <div class=" form-group" id="permanent">
                    <div class="col-md-2">
                        <label class="control-label">Permanent address</label>
                    </div>
                    <div class="col-md-6">
                        <textarea cols="50" rows="5" disabled class="form-control disabled"></textarea>
                    </div>

                </div>

                <div id="otheraddress">
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-2">
                            Street :
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-2">
                            <input type='text' class="form-control" name='street'  maxlength='45'/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-2">
                            City :
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-2">
                            <input type='text' class="form-control" name='city'  maxlength='45'/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-2">
                            State :
                        </div><div class="col-xs-4 col-md-2 col-lg-2">
                            <input type='text' class="form-control" name='state'  maxlength='45'/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-4 col-md-2 col-lg-2">
                            Zip code :
                        </div>
                        <div class="col-xs-4 col-md-2 col-lg-2">
                            <input type='number'  class="form-control" name='pincode'  maxlength='6'/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 btn btn-primary pull-right">
                        <input type="submit">
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
    <script>
                $(document).ready(function() {
        $("#add").change(funnction(){
        if ($("#add").prop("checked", true).val() == 0){
        $("#permanent").show(100);
                $("#otheraddress").hide(100);
        } else{
        $("#otheraddress").show(100);
                $("#permanent").hide(100);
        }
        });
        });
                function clearError() {
                $("#error").hide();
                }
        function checkInput(id) {

        if (!($("#" + id).val())) {
        $("#error").show();
                return false;
        } else {
        $("#error").hide();
                return true;
        }
        }
        function addRow() {
        var no = parseInt($("#noOfStyles").val(), 10);
                if (!checkInput("styleName_" + (no))) {
        $("#styleDiv").append("");
                return;
        }
        no = no + 1;
                var name = "styleName_" + no;
                $("#noOfStyles").val("" + no);
                var divSubmit = $(document.createElement('div'));
                divSubmit.attr("class", "row form-group");
                //                console.log("created");

                $(divSubmit)
                .append(
                        '<div class = "col-md-6" > <input onfocus="clearError()"  type = "text" placeholder = "Style Name" onblur="checkInput(\''
                        + name
                        + '\')" id="'
                        + name
                        + '" class = "form-control" name="'
                        + name
                        + '" > </div>  <div class = "col-md-1" > <input type = "button" value = " + " class="myclass" name="add"  onclick="addRow()"> </div> </div>');
                //                console.log("appended");
                $("#styleDiv").append(divSubmit);
                //                add(divSubmit);
                //                console.log("added");
        }
        ;
    </script>
</body>
</html>
