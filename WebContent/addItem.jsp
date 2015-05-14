

<%@page import="com.model.Size"%>
<%@page import="com.model.Color"%>
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
        %>
        <h1 style="color: darkgray">Add Item</h1>
        </hr>

        </br>
        <%
            List<Category> categories = null;
        %>
        <br>

        <p><%=msg%></p>
        <!--<h5 style="color:red">Could not add category. Categoryname is null</h5>-->
        <form action="AddItem" method="post" class="form-horizontal">
            <input type="hidden" name="noOfDetailEntries" id="noOfDetailEntries" />
            <div class="table col-xs-8 col-md-8 col-md-offset-1">
                <div class="row form-group">
                    <div class="col-xs-2 col-md-2"><label class="control-label">Item Name:</label></div>
                    <div class="col-xs-6 col-md-6"><input class="form-control" placeholder="name" type="text" name="name"></div>
                </div>
                <div class=" row form-group">

                    <div class="col-xs-2 col-md-2">
                        <label class="control-label">Item price:</label></div>
                    <div class="col-xs-6 col-md-6"><input type="text" class="form-control" placeholder="Price" name="price" id="price"></div>
                </div>
                <div class="row form-group">
                    <div class="col-xs-3 col-md-3"><label class="control-label">Color</label></div>
                    <div class="col-xs-3 col-md-3"><label class="control-label">Size</label></div>
                    <div class="col-xs-3 col-md-3"><label class="control-label">Stock</label></div>

                </div>
                <div id="styleDiv">
                    <div class="form-group">
                        
                        <div class="col-xs-3 col-md-3"><select class="form-control" id="color_0" name="color_0">

                            </select></div>
                        <div class="col-xs-3 col-md-3"><select class="form-control" id="size_0" name="size_0">

                            </select>
                        </div>
                        <div class="col-xs-3 col-md-3"><input type="number" value="0" class="form-control" id="stock_0" name="stock_0" min="0">

                        </div>
                        <div class="col-xs-1 col-md-1"><input type="button" value=" + " class="myclass" id="addButton" name="add" onclick="addRow()">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-2 col-md-2"><label class="control-label">Category</label></div>
                        <div class="col-xs-6 col-md-6"><select class="form-control" id="category" name="category">
                                <option value="null">Select Category</option>
                                <%
                                    if (categories != null) {
                                        for (Category category : categories) {
                                        	
                                %>
                                <option value="<%=category.getId()%>"> <%=category.getCategoryName()%></option>
                                <%
                                        }
                                    }
                                %>
                            </select></div>
                    
                </div>
                <div class="row form-group">
                    <div class="col-xs-2 col-md-2"><label class="control-label">Style</label></div>
                    <div class="col-xs-6 col-md-6"><select class="form-control" id="style" name="style">

                        </select></div>

                </div>
                <div class="row">
                    <div class="col-xs-1 col-xs-12 col-md-12 pull-right"><input type="submit"></div>
                </div>
            </div>
        </div>


    </form>
    <script>
        var colors = [];
        var sizes = [];
        var categories = [];
        $(document).ready(function() {
            $("#noOfDetailEntries").val("0");
            $("#error").hide();

            $("#category").change(function() {
                var selectedValue = $("#category").find(":selected").val();
//                alert(selectedValue);
                $.get("AddItem", {'selectedcategory': selectedValue}, function(data, status) {
                    var $select = $('#style');
                    $select.find('option').remove();
//                    var object = JSON.parse(data);
//                    alert(object + "  ");
                    $.each(data, function(key, value) {
                        $('<option>').val(value.id).text(value.name).appendTo($select);
                    });
//                        
                });
            });

            $.get("AddItem", {'get': "colors"}, function(data, status) {
                colors = data;
                var $select = $('#color_0');
                $select.find('option').remove();
//                    var object = JSON.parse(data);
//                    alert(object + "  ");
                $.each(data, function(key, value) {
                    $('<option>').val(value.id).text(value.name).appendTo($select);
                });
            });
            $.get("CategoriesServlet", {'get': "categories"}, function(data, status) {
               // categories = data;
             
                var $select = $('#category');
                $select.find('option').remove();
                    //var object = JSON.parse(data);
                    //alert(object + "  ");
				 $('<option>').val(null).text("Select Category").appendTo($select);
				 $(data).each(function(key, value){
					 $('<option>').val(value.id).text(value.categoryName).appendTo($select);
				    });
                
            });
            $.get("AddItem", {'get': "sizes"}, function(data, status) {
                sizes = data;
                var $select = $('#size_0');
                $select.find('option').remove();
//                    var object = JSON.parse(data);
//                    alert(object + "  ");
                $.each(data, function(key, value) {
                    $('<option>').val(value.id).text(value.sizeCode).appendTo($select);
                });
            });

        });
        function fillColor(id) {
            var $select = $('#' + id);
            $select.find('option').remove();
//                    var object = JSON.parse(data);
//                    alert(object + "  ");
            $.each(colors, function(key, value) {
                $('<option>').val(value.id).text(value.name).appendTo($select);
            });
        }
        function fillSize(id) {
            var $select = $('#' + id);
            $select.find('option').remove();
//                    var object = JSON.parse(data);
//                    alert(object + "  ");
            $.each(sizes, function(key, value) {
                $('<option>').val(value.id).text(value.sizeCode).appendTo($select);
            });
        }
        function fillCategory(id) {
            var $select = $('#' + id);
            $select.find('option').remove();
//                    var object = JSON.parse(data);
//                    alert(object + "  ");
            $.each(categories, function(key, value) {
                $('<option>').val(value.id).text(value.name).appendTo($select);
            });
        }
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
            var no = parseInt($("#noOfDetailEntries").val(), 10);
//            console.log("in add row");
            no = no + 1;
            var colorname = "color_" + no;
            var sizename = "size_" + no;
            var stockname = "stock_" + no;

            $("#noOfDetailEntries").val("" + no);
            var divSubmit = $(document.createElement('div'));
            divSubmit.attr("class", "col-md-12");
//            console.log("created");
            $(divSubmit).append('<div class="row form-group"> <div class="col-xs-3 col-md-3"><select class="form-control" id="' + colorname + '" name="' + colorname + '"> </select></div> <div class="col-xs-3 col-md-3"><select class="form-control" id="' + sizename + '" name="' + sizename + '"> </select> </div><div class="col-xs-3 col-md-3"><input type="number" value="0" min="0" class="form-control" id="' + stockname + '" name="' + stockname + '">  </div> <div class="col-xs-1 col-md-1"><input type="button" value=" + " class="myclass" id="addButton" name="add" onclick="addRow()"> </div> </div>');
//            console.log("appended");
            $("#styleDiv").append(divSubmit);
            fillColor(colorname);
            fillSize(sizename);
//                            add(divSubmit);
//            console.log("added");
        }
        ;

    </script>
</body>
</html>
