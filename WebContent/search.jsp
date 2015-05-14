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
        <link href="css/star-rating.min.css" media="all" rel="stylesheet" type="text/css" />
        <script src="scripts/jquery-2.1.3.min.js"></script> 
        <script src="scripts/star-rating.min.js" type="text/javascript"></script>


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
        <% if (loggedIn == null) {
                loggedIn = "";
            }
            List<Category> categories = null;
            categories = (List<Category>) application.getAttribute("categories");

            Object category = request.getAttribute("items");

            if (category != null && category instanceof List) {
                List<Item> items = (List<Item>) category;
        %>
        <p><%= msg%></p>

        <div class="col-xs-12 col-md-12 col-lg-12">
            <div class="col-xs-10 col-md-8 col-lg-8 input-group">
                <span class="input-group-addon  glyphicon glyphicon-search"></span>
                <input type="search" placeholder="Search Item" class="form-control" onkeyup="searchItem()" id="search">
            </div>
            <div class="row" id="searchResult">
                <div class="col-xs-4 col-md-3 col-lg-3">Item name</div><div class="col-xs-4 col-md-3 col-lg-3">Style</div><div class="col-xs-2 col-md-2 col-lg-2">Price</div><div class="col-xs-3 col-md-3 col-lg-3">Seller</div>

            </div>
            <div class="row">
                <div class="col-xs-6 col-md-2 col-lg-2"><label class="control-label">Item</label></div><div class="col-xs-6 col-md-2 col-lg-2"><label class="control-label">Price</label></div>
            </div>
            <%
                request.setAttribute("items", items);
                for (Item item : items) {

            %>

            <div class="row">
                <div class="col-xs-6  col-md-4 col-lg-4">
                    <input type="button" class="btn btn-link" value="<%=item.getItemName()%>" onclick="loadItemDetails(<%=item.getItemid()%>)" >

                </div>
                <div class="col-xs-6 col-md-4 col-lg-4"><label class="control-label">$<%= item.getPrice()%></label></div>
            </div>
            <%
                    }
                } else {

                }%>
        </div>
        <br/>
        <br/>
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
                <div class="col-xs-12 col-md-12 col-lg-12">
                    <div class="col-xs-3 col-md-2 col-lg-2"><label class="control-label">User Ratings</label></div>
                    <div id="ratings">
                       
                    </div> 
                </div>


            </div>
        </div>
        <!--<button onclick="onAddToCart()" class="btn btn-toolbar"><span class=" glyphicon glyphicon-shopping-cart"></span> </button>-->
        <script >
            function searchItem() {
                var keyword = $("#search").val();
//                alert(keyword);
                $.get('SellerItemsServlet', {'getItemByName': 'getItemByName', 'keyword': keyword}, function (data) {
                    $("#searchResult").empty();
                    $("#searchResult").append('<div class="row"><div class="col-xs-4 col-md-3 col-lg-3">Item name</div><div class="col-xs-4 col-md-3 col-lg-3">Style</div><div class="col-xs-2 col-md-2 col-lg-2">Price</div><div class="col-xs-3 col-md-3 col-lg-3">Seller</div></div>');
                    console.log(data);
                    var i = 0;
                    $.each(data, function (key, value) {
//                        alert(value.firstName);
                        var id = "id_" + i;
                        $("#searchResult").append('<input type="hidden"id="' + id + '" value="' + value.itemid + '"><div class="row"><div class="col-xs-4 col-md-3 col-lg-3">' + value.itemName + '</div><div class="col-xs-4 col-md-3 col-lg-3">' + value.style.name + '</div><div class="col-xs-2 col-md-2 col-lg-2">$' + value.price + '</div><div class="col-xs-3 col-md-3 col-lg-3">' + value.seller.user.userId + '</div><div class="col-xs-1 col-md-2 col-lg-2"><button class="btn btn-toolbar" onclick="loadItemDetails(' + value.itemid + ')"><span class="glyphicon glyphicon-ok"></span></button></div></div>');
                        i++;
                    });
                });
            };

            function loadItemDetails(id) {

                $.get("SearchItemServlet", {'item': id}, function (data, status) {
                    $.get('UserRatingServlet', {'item': id, 'getRatings': 'getRatings'}, function (data) {
                        $("#ratings").empty();
                        var i=0;
//                        $.each([1,2,3,4,5,6,7],function(val){
                           
                        $.each(data,function(key,val){
                        var divSubmit = $(document.createElement('div'));
                        divSubmit.attr("class", "col-md-12 col-xs-12 col-lg-12");
//            console.log("created");
                        $(divSubmit).append('<label  >'+val.user.firstName + " " + val.user.lastName+'</label><input readonly="true" id="input-id_'+i+'" value="'+val.rating+'" type="number" class="rating" min="1" max="5" step="1" data-size="sm" data-rtl="false"><label >'+val.comment+'</label>');
//            console.log("appended");
                        $("#ratings").append(divSubmit);
                        $("#input-id_"+i).rating();
                         i++;
                    });
                    });
                    $("#itemDetails").show();
                    item = data;
                    //                alert(data + "  " + data.itemDetails.length);
                    $("#itemName").html(data.itemName);
                    $("#price").html("$" + data.price);
                    alert(data.style);
                    if(null!=data.style)
                    $("#style").html(data.style.name);
                    $("#detailsDiv").empty();
                    for (var i = 0; i < data.itemDetails.length; i++) {
                        var colorName = "color_" + i;
                        var sizeName = "size_" + i;
                        var itemdetail = data.itemDetails[i];
                        var divSubmit = $(document.createElement('div'));
                        divSubmit.attr("class", "row form-group");
                        //            console.log("created");
                        $(divSubmit).append('<div class="col-xs-5 col-md-5 col-lg-5"> <div class="col-md-1 col-lg-1"><label class="control-label">Color </label></div> <div class="col-xs-4 col-md-4 col-lg-4"><label class="control-label" id="' + colorName + '">' + itemdetail.color.name + ' </label></div> </div> <div class="col-xs-6 col-md-6"> <div class="col-md-2 col-lg-2"><label class="control-label">size</label></div> <div class="col-xs-4 col-md-4 col-lg-4"><label class="control-label" id="' + sizeName + '">' + itemdetail.size.sizeCode + ' </label></div> </div><div class="col-xs-1 col-md-1"> <button  onclick="onAddToCart(' + i + ')" type="button"  class="btn btn-toolbar"><span class=" glyphicon glyphicon-shopping-cart"></span> </button> </div>');
                        //            console.log("appended");
                        $("#detailsDiv").append(divSubmit);
                    }

                });
            }
            
            $(document).ready(function () {

			//loadItemDetails(id);
                $("#itemDetails").hide();
                // initialize with defaults
                $("#input-id").rating();
// with plugin options
//                $("#input-id").rating(['min' = > 1, 'max' = > 5, 'step' = > 1, 'size' = > 'sm']);
            });

            function onAddToCart(id) {
                var cartString = localStorage.getItem("cart");
                var cart;
                console.log(!cartString);
                if (!cartString) {
                    cart = {};
                } else {
                    cart = JSON.parse(cartString);
                }
                var cartEntery = {'id': item.itemid, 'name': item.itemName, 'price': item.price, 'style': item.style, 'seller': item.seller, 'selectedItem': item.itemDetails[id],'quantity':1};
				var id=item.itemid + "-" + cartEntery.selectedItem.color.id + "-" + cartEntery.selectedItem.size.id;
				if(null==cart[id]){
                	cart[id] = cartEntery;
                	//            alert(JSON.stringify(cart))
                	localStorage.setItem("cart", JSON.stringify(cart));
            	}else{
					alert("item is already exist in the cart");
                }	
            }
            ;
           
        </script>
    </body>
</html>
