<%-- 
    Document   : search
    Created on : 13 Apr, 2015, 1:37:17 AM
    Author     : shruti
--%>

<%@page import="com.model.Category"%>
<%@page import="com.model.ItemDetail"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Item"%>
<%@page import="com.model.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0;" />
        <title>Search</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
        <link href="fonts/glyphicons-halflings-regular.ttf"/>
        <link  href="fonts/glyphicons-halflings-regular.woff"/>
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
        <h1 style="color:green">Search</h1>
        <p><%= msg%></p>
        <select name="category" id="category">
            <% if (loggedIn == null) {
                    loggedIn = "";
                }
                List<Category> categories = null;
                categories = (List<Category>) application.getAttribute("categories");
                if (categories != null) {
                    for (Category category : categories) {
            %>
            <option value="<%=category.getId()%>"><%=category.getCategoryName()%></option>
            <%
                    }
                }
            %>
        </select>


        <div class="col-xs-12 col-md-12 col-lg-12">
            <div class="col-xs-10 col-md-8 col-lg-8 input-group">
                <span class="input-group-addon  glyphicon glyphicon-search"></span>
                <input type="search" placeholder="Search Item" class="form-control" onkeyup="searchItem()" id="search">
            </div>
            <div class="row" id="searchResult">
                <div class="col-xs-4 col-md-3 col-lg-3">Item name</div><div class="col-xs-4 col-md-3 col-lg-3">Style</div><div class="col-xs-2 col-md-2 col-lg-2">Price</div><div class="col-xs-3 col-md-3 col-lg-3">Seller</div>

            </div>

        </div>
        </br>
        </br>
        <div class="col-xs-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1" id="itemDetails">
            <h1>Item Details</h1>

            <div >
                <%
                    Object itemObject = request.getAttribute("item");
                    //            if (itemObject != null) {
                    Item item = (Item) itemObject;
                %>

                <div class="row">
                    <div class="col-xs-3 col-md-2 col-lg-2"><label class="control-label">Item Name:</label></div>
                    <div class="col-xs-3 col-md-4 col-lg-4"><label class="control-label" id="itemName"> </label></div>
                </div>
                <div class="row">
                    <div class="col-xs-3 col-md-2 col-lg-2"><label class="control-label"> Price:</label></div>
                    <div class="col-xs-3 col-md-4 col-lg-4"><label class="control-label" id="price"> </label></div>
                </div>
                <div class="row">
                    <div class="col-xs-3 col-md-2 col-lg-2"><label class="control-label">Style:</label></div>
                    <div class="col-xs-3 col-md-4 col-lg-4"><label class="control-label" id="style"> </label></div>
                </div>
                <div class="row">
                    <div class="col-xs-3 col-md-2 col-lg-2"><label class="control-label">Options:</label></div>
                    <div class="col-xs-9 col-md-8" id="detailsDiv">
                        <div class="row">

                        </div>
                    </div>

                </div>

            </div>
        </div>
        <!--<button onclick="onAddToCart()" class="btn btn-toolbar"><span class=" glyphicon glyphicon-shopping-cart"></span> </button>-->
        <script >
            function searchItem() {
                var keyword = $("#search").val();
//                alert(keyword);
                $.get('SellerItemsServlet', {'getItemByName': 'getItemByName', 'keyword': keyword}, function(data) {
                    $("#searchResult").empty();
                    $("#searchResult").append('<div class="row"><div class="col-xs-4 col-md-3 col-lg-3">Item name</div><div class="col-xs-4 col-md-3 col-lg-3">Style</div><div class="col-xs-2 col-md-2 col-lg-2">Price</div><div class="col-xs-3 col-md-3 col-lg-3">Seller</div></div>');
                    console.log(data);
                    var i = 0;
                    $.each(data, function(key, value) {
//                        alert(value.firstName);
                        var id = "id_" + i;
                        $("#searchResult").append('<input type="hidden"id="' + id + '" value="' + value.itemid + '"><div class="row"><div class="col-xs-4 col-md-3 col-lg-3">' + value.itemName + '</div><div class="col-xs-4 col-md-3 col-lg-3">' + value.style.name + '</div><div class="col-xs-2 col-md-2 col-lg-2">$' + value.price + '</div><div class="col-xs-3 col-md-3 col-lg-3">' + value.seller.user.userId + '</div><div class="col-xs-1 col-md-2 col-lg-2"><button class="btn btn-toolbar" onclick="loadItemDetails(' + value.itemid + ')"><span class="glyphicon glyphicon-ok"></span></button></div></div>');
                        i++;
                    });
                });
            }

            function selectItem(id) {

            }
            $(document).ready(function() {
                $("#itemDetails").hide();
            });
            function loadItemDetails(id) {

                $.get("SearchItemServlet", {'item': id}, function(data, status) {
                    $("#itemDetails").show();
                    item = data;
                    //                alert(data + "  " + data.itemDetails.length);
                    $("#itemName").html(data.itemName);
                    $("#price").html("$" + data.price);
                    $("#style").html(data.style.name);
                    $("#detailsDiv").empty();
                    for (var i = 0; i < data.itemDetails.length; i++) {
                        var colorName = "color_" + i;
                        var sizeName = "size_" + i;
                        var itemdetail = data.itemDetails[i];
                        var divSubmit = $(document.createElement('div'));
                        divSubmit.attr("class", "row form-group");
                        //            console.log("created");
                        $(divSubmit).append('<div class="col-xs-5 col-md-5 col-lg-5"> <div class="col-md-1 col-lg-1"><label class="control-label">Color </label></div> <div class="col-xs-4 col-md-4 col-lg-4"><label class="control-label" id="' + colorName + '">' + itemdetail.color.name + ' </label></div> </div> <div class="col-xs-6 col-md-6"> <div class="col-md-2 col-lg-2"><label class="control-label">size</label></div> <div class="col-xs-3 col-md-3 col-lg-3"><label class="control-label" id="' + sizeName + '">' + itemdetail.size.sizeCode + ' </label></div> </div><div class="col-xs-1 col-md-1 col-lg-1"><input type="number" name="stock_'+i+'" id="stock_'+i+'" min="0"></div><div class="col-xs-1 col-md-1"> <button  onclick="onsave(' + i + ')" type="button"  class="btn btn-primary"><span class=" glyphicon glyphicon-shopping-floppy-disk"></span> </button> </div>');
                        //            console.log("appended");
                        $("#detailsDiv").append(divSubmit);

                    }

                });
            }
            ;

            function onsave(id) {
              $.post('/AddStock',{'id':item.itemid,'color':item.itemDetails[id].color.id,'size':item.itemDetails[id].size.id},function(){
                  
              });
                var cartEntery = {'id': item.itemid, 'name': item.itemName, 'price': item.price, 'style': item.style, 'seller': item.seller, 'selectedItem': item.itemDetails[id]};

                cart[item.itemid + "-" + cartEntery.selectedItem.color.id + "-" + cartEntery.selectedItem.size.id] = cartEntery;
                //            alert(JSON.stringify(cart))
                localStorage.setItem("cart", JSON.stringify(cart));
            }
            ;

        </script>
    </body>
</html>
