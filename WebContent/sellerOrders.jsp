
<%@page import="com.model.User"%>
<%@page import="com.dao.AuthDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
        <link href="fonts/glyphicons-halflings-regular.ttf"/>
        <link  href="fonts/glyphicons-halflings-regular.woff"/>
        <script src="scripts/jquery-2.1.3.min.js"></script> 
        <script src="scripts/bootstrap/bootstrap.min.js"></script>
    </head>
    <script type="text/javascript">
	$(document).ready(function() {
		$('#body').css('left','100px');
		$('#body').css('top','700px');
	});
    </script>
    <body>
        <div class="col-md-10 col-lg-10">
            <h1 style="color:darkgray; " class="col-md-12 col-lg-12" >Order Details</h1>
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
            <div id="msg"></div>
        <div id="items" class="col-xs-12 col-md-12 col-lg-12">
            <div class="row">
                <div class="col-xs-1 col-md-1 col-lg-1  control-label" >Order Id</div><div class="col-xs-2 col-md-2 col-lg-2 control-label" >Ordered On</div><div class="col-xs-2 col-md-2 col-lg-2 control-label" >Delivered On / Estimated Delivery Date</div><div class="col-xs-2 col-md-2 col-lg-2  control-label" >Buyer Name</div><div class="col-xs-1 col-md-1 col-lg-1 control-label" >Status</div><div class="col-xs-2 col-md-2 col-lg-2 control-label" >Payment Method</div><div class="col-xs-1 col-md-1 col-lg-1 control-label" >Total Amount</div><div class="col-xs-1 col-md-1 col-lg-1 control-label" >Action</div>
           <div id="data"></div>
           
        </div>
		</div>
        <!--Edit order Modal -->
        <div class="modal fade" id="myModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form id="updateForm" class="form-horizontal">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">Edit order</h4>
                        </div>
                        <div class="modal-body editOrder">


                            <fieldset>
                                <input type="hidden" id="seller" name="seller">
                                <input type="hidden" id="orderId" name="orderId">
                                <div class="form-group">
                                    <div class=" col-xs-4 col-md-2 col-lg-2">
                                        <label for="orderid" class="control-label">Order Id</label>
                                    </div>
                                    <div class=" col-xs-8 col-md-6 col-lg-6">
                                        <input id="orderid" name="orderid"  class="form-control disabled" disabled type="text" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class=" col-xs-4 col-md-2 col-lg-2">
                                        <label for="orderedOn" class="control-label">Order Date</label>
                                    </div>
                                    <div class=" col-xs-8 col-md-6 col-lg-6">
                                        <input id="orderedOn" name="orderedOn"  class="form-control disabled" disabled type="date" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class=" col-xs-4 col-md-2 col-lg-2">
                                        <label for="shippedOn" class="control-label">Delivery Date</label>
                                    </div>
                                    <div class=" col-xs-8 col-md-6 col-lg-6">
                                        <input id="shippedOn" name="shippedOn"  class="form-control" type="date" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class=" col-xs-4 col-md-2 col-lg-2">
                                        <label for="total" class="control-label">Delivery Date</label>
                                    </div>
                                    <div class=" col-xs-8 col-md-6 col-lg-6">
                                        <input id="total" name="total"  class="form-control" disabled type="number" >
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class=" col-xs-4 col-md-2 col-lg-2">
                                        <label for="status" class="control-label">Status</label>
                                    </div>
                                    <div class=" col-xs-8 col-md-6 col-lg-6">
                                        <select id="status" name="status"  class="form-control" >
                                            <option value="Active">Active</option>
                                            <option value="Pending">Pending</option>
                                            <option value="Sent">Sent</option>
                                            <option value="Delivered">Delivered</option>
                                            <option value="Canceled">Canceled</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-12 col-md-12 col-lg-12">
                                        <label for="other" class="control-label">Items</label>
                                    </div>
                                    <div class=" col-xs-8 col-md-6 col-lg-6">
                                        <div class="col-xs-2 col-md-2 col-lg-2"><label class="control-label">Name</label></div><div class="col-xs-2 col-md-2 col-lg-2"><label class="control-label">Color</label></div><div class="col-xs-2 col-md-2 col-lg-2"><label class="control-label">Size</label></div><div class="col-xs-2 col-md-2 col-lg-2"><label class="control-label">Quantity</label></div><div class="col-xs-2 col-md-2 col-lg-2"><label class="control-label">Price</label></div>
                                        <div class="col-xs-12 xol-md-12 col-lg-12" id="orderItems"></div>
                                        <!--<div class="col-xs-2 col-md-2 col-lg-2">Name</div><div class="col-xs-2 col-md-2 col-lg-2">Color</div><div class="col-xs-2 col-md-2 col-lg-2">Color</div><div class="col-xs-2 col-md-2 col-lg-2"><button class=" btn btn-primary" title="Remove item from order"><span class="glyphicon glyphicon-remove"></span></button></div>-->
                                    </div>
                                </div>
                            </fieldset>


                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        
        <!--Modal end-->

<div id="delcom" style="display: none;">

</div>

        <!--<div class="col-md-2 col-lg-2 btn-group" onclick="onEditClick(1, '25-10-2015', '25-10-2015', 'Active')"><button class="btn btn-primary" title="Edit order"><span class="glyphicon glyphicon-edit"></span></button><button title="Delete order" class="btn btn-primary"><span class="glyphicon glyphicon-remove"></span></button></div>-->
        <script>

            var orderData;
            var id = 0;
            var selectedseller = 0;
            function updateFormSubmit() {


            }
            function appendZero(date) {
                return ("0" + (date)).slice(-2);
            }
            function onEditClick(i) {
                id = i;
                $("#myModal").modal('show');
//                var selectedSeller=$("#seller").find(":selected").val();
                console.log('selected</form>Seller ' + selectedseller)
            }
            function fillItems(selectedValue) {
//                alert(selectedValue)
                $.get("OrderServlet", {'getOrderForSeller': 'getOrderForSeller', 'seller': selectedValue}, function(data) {
//                  var object = JSON.parse(data);
//                  alert(object + "  ");
                    $("#data").empty();
                    orderData = data;
                    var i = 0;
                    $.each(data, function(key, value) {

//            console.log("created");
                        var divSubmit = $(document.createElement('div'));
//                        alert("value.buyer" + JSON.stringify(value.buyer));
                        divSubmit.attr("class", "col-xs-12 col-md-12 col-lg-12");
                        divSubmit.attr("id","id_"+i);
                         $(divSubmit).append('<div class="col-xs-1 col-md-1 col-lg-1  control-label" >' + value.orderID + '</div><div class="col-xs-2 col-md-2 col-lg-2 control-label" >' + value.orderTime + '</div><div class="col-xs-2 col-md-2 col-lg-2 control-label" >' + value.shippedTime + '</div><div class="col-xs-2 col-md-2 col-lg-2  control-label" >' + value.buyer.firstName + " " + value.buyer.lastName + '</div><div class="col-xs-1 col-md-1 col-lg-1 control-label" >' + value.orderStatus + '</div><div class="col-xs-2 col-md-2 col-lg-2 control-label" >' + value.payment + '</div><div class="col-xs-1 col-md-1 col-lg-1 control-label" >' + value.totalAmount + '</div><div class="col-xs-2 col-md-1 col-lg-1"><button class="btn btn-primary btn-xs" onclick="onEditClick(' + i + ')" title="Edit order"><span class="glyphicon glyphicon-edit"></span></button><button title="Delete order" class="btn btn-primary  btn-xs" onclick="deleteOrder('+i+','+value.orderID+')"><span class="glyphicon glyphicon-remove"></span></button></div>'); 
                        
                        $("#data").append(divSubmit);
                        i++;
                    });

                });
            }
            $(document).ready(function() {
                $('#msg').empty();
                $("#updateForm").submit(function(e)
                {
                    console.log("in updateforem")
                    var postData = $(this).serializeArray();
                    var formURL = 'UpdateOrderServlet';
                    $.ajax(
                            {
                                url: formURL,
                                type: "POST",
                                data: postData,
                                success: function(data, textStatus, jqXHR)
                                {
                                    //data: return data from server
                                },
                                error: function(jqXHR, textStatus, errorThrown)
                                {
                                    //if fails      
                                }
                            });
                    e.preventDefault(); //STOP default action
//                    e.unbind(); //unbind. to stop multiple form submit.
                });

//                $("#updateForm").submit(); //Submit  the FORM
                $.get("SellerItemsServlet", {'get': 'seller'}, function(data) {
                    var $select = $('#seller1');
                    $select.find('option').remove();
                    $.each(data, function(key, value) {
                        $('<option>').val(value.userId).text(value.firstName + " " + value.lastName).appendTo($select);
                    });
                });
                var selectedValueseller = $("#seller").find(":selected").val();
                var selectedValueadmin = $("#seller1").find(":selected").val();
                if (selectedValueadmin) {
                    selectedseller = selectedValueadmin;
                    fillItems(selectedValueadmin);
                }
                if (selectedValueseller) {
                    selectedseller = selectedValueseller;
                    fillItems(selectedValueseller);
                }
                $("#seller1").change(function(data) {
                    var selectedValue = $("#seller1").find(":selected").val();
                    fillItems(selectedValue);
                });
                $('#myModal').on('show.bs.modal', function(e) {
                    console.log(e);
                    $.get('OrderItemServlet', {'getOrderItemForSeller': 'getOrderItemForSeller', 'seller': selectedseller, 'id': orderData[id].orderID}, function(data) {
                        console.log(data);
                        $.each(data, function(key, val) {
                            var divSubmit = $(document.createElement('div'));
                            divSubmit.attr("class", "row form-group");
                            $(divSubmit).append(val.itemDetail.item.itemName + "\t\t" + val.itemDetail.color.name + '      ' + val.itemDetail.size.sizeCode + '     ' + val.qty + '     $' + val.itemDetail.item.price + '  ');
                            $(".editOrder #orderItems").append(divSubmit);
                        });

                    });

                    var orderdate = new Date(orderData[id].orderTime);
                    var shippedTime = new Date(orderData[id].shippedTime);
                    console.log(orderdate.getFullYear() + "-" + orderdate.getMonth() + "-" + orderdate.getDate());
                    $(".editOrder #seller").val(selectedseller);
                    $(".editOrder #orderId").val(orderData[id].orderID);
                    $(".editOrder #orderid").val(orderData[id].orderID);
                    $(".editOrder #orderedOn").val(orderdate.getFullYear() + "-" + appendZero(orderdate.getMonth() + 1) + "-" + appendZero(orderdate.getDate()));
                    $(".editOrder #shippedOn").val(shippedTime.getFullYear() + "-" + appendZero(shippedTime.getMonth() + 1) + "-" + appendZero(shippedTime.getDate()));
                    $(".editOrder #status").val(orderData[id].orderStatus);
                    $(".editOrder #total").val(orderData[id].totalAmount);

                });
            });
            function deleteOrder(index,orderId){
                $('#msg').empty();
                if (confirm("Are you sure?")) {
            		$.post('OrderServlet', {'deleteOrder': 'deleteOrder',  'id': orderId}, function(data,status) {

            			if("success"==status){
            		      $('#msg').html('<h5 style="color:green">Order is deleted successfully.</h5>');
						  $("#id_"+id).remove();
                		}
              		  });
               	 }
             }
        </script>
    </body>
</html>