<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link href="fonts/glyphicons-halflings-regular.ttf" />
<link href="fonts/glyphicons-halflings-regular.woff" />
<script src="scripts/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="scripts/clothing/index.js"></script>

</head>
<body>

	<div class="col-md-12 col-lg-12" style="left:">
		<h1 style="color: darkgray">My cart</h1>
		<div class="col-md-12">
			
				<table id="cart" class="table">
					
					<tr>
						<th></th>
						<th class="col-md-2 col-lg-2">Item</th>
						<th class="col-md-1 col-lg-1">Price</th>
						<th class="col-md-1 col-lg-1">Color</th>
						<th class="col-md-1 col-lg-1">Size</th>
						<th class="col-md-2 col-lg-2">Style</th>
						<th class="col-md-1 col-lg-1">Quantity</th>
						<th class="col-md-1 col-lg-1">Total</th>
						<th>Action</th>
					</tr>
					<!--<tr id="row1"> <td><input type="hidden" id="id"></td> <td></td> <td></td> <td></td> <td></td> <td></td> <td><input type="number" min="1" value="1" class="form-control" name="qty_" id="qty_"></td> <td><button class=" btn btn-success" onclick='deleteTableRow("row"+1)'><span class="glyphicon glyphicon-trash" ></span></button></td> </tr>-->
					<!--                        <tr id="btnarea"><td ><input type="submit" class="btn btn-success pull-right"></td></tr>-->
					<!--<tr> <td ></td><td ></td><td ></td><td ></td><td ></td><td ></td><td ></td><td>Grand Total</td> <td><input type='number' class="disabled" disabled="true" value=""></td></tr>-->
				</table>
		</div>
	</div>

	<script>
		var noOfitems = 0;
		$(document)
				.ready(
						function() {

							$('#body').css('left', '100px');
							$
									.get(
											'ShowCartServlet',
											function(data, status) {
												if ('success' == status) {
													console.log(data);
													$(
													"#cart").empty();
													var cart = JSON.parse(data);
													var cartId =cart.cartId;
													$('#cartId').val(cartId);
													console.log(cartId);
													var i = 0;
													var grandTotal = 0;
													var items = cart.items;

													var i = 0;
													var grandTotal = 0;
													$
															.each(
																	items,
																	function(
																			key,
																			value) {
																		if (null != value) {
																			console
																					.log(cart);

																			var rowname = "row_"
																					+ i;
																			var item = value.item;
																			if ('undefined' == value.quantity) {
																				value.quantity = 1;
																			}
																			var total = item.price
																					* value.quantity;

																			grandTotal += total;
																			var index = key;
																			var styleName = '';
																			if (null != item.style) {
																				styleName = item.style.name;
																			}
																			var itemId=item.itemid;


														                     $("#cart").append('<tr id="' + rowname + '"> <td><input type="hidden" id="id_' + i + '" name="id_' + i + '" value="' + key + '"></td> <td>' + item.itemName + '</td> <td>$' + item.price + '</td> <td>' + item.itemDetails[0].color.name+ '</td> <td>' + item.itemDetails[0].size.sizeCode + '</td> <td>' + styleName + '</td><td><input  type="number" value='+value.quantity+' min="1" class="form-control" name="qty_' + i + '" id="qty_'+i+'" onchange=\"updateQuantity('+i+','+item.price+','+item.itemid+')\"><td id="total_'+i+'">$' + total + '</td>  <td><button class=" btn btn-toolbar" onclick=\'deleteTableRow("' + rowname + '","' + i + '","' + item.price + '","' + key +'","' + item.itemid+ '")\'><span class="glyphicon glyphicon-trash" ></span></button></td> </tr>');
																			
																			i++;

																		}
																	});
													noOfitems = i--;
														if (0 != noOfitems){
															$('#cart')
															.append(
																	'<tr> <td ></td><td ></td><td ></td><td ></td><td ></td><td ></td><td><label class="control-lable pull-right"> Grand Total</label></td> <td><label class="control-lable pull-left">$<span id="grandTotal">'
																			+ grandTotal
																			+ '</span></label></td><td></td></tr>');
												
															$('#cart')
															.append(
																	'<tr id="btnarea"><td ></td><td ></td><td ></td><td ></td><td ></td><td ></td><td></td><td><a href="https://paypal.com" target="_blank"><img src="images/paypal.jpg"/></a></td><td ><input type="button" value="Pay" class="btn btn-toolbar btn-lg pull-left pull-right" onclick="loadjsp('+cartId+')"></td></tr>');

												
															}else{
																$('#cart').append("<h5> Cart is empty.</h5>");
																}
														$("#noOfitems").val(
															noOfitems);
												}
											});
						});
		function loadjsp(cartId) {
			$('#filter').empty();
			$('#subcat').empty();
			$('#body').empty();
			$('#body').load('payment.jsp');
		}
		function deleteTableRow(id, i, price, itemKey, itemId) {

			var quantity = $("#qty_" + i).val();

			var grandTotal = $("#grandTotal").html();
			var x = parseInt(grandTotal) - (price * parseInt(quantity));		
	
			$.post('DeleteCartItemServlet', {
				'itemId' : itemId
			}, function(data, status) {
				console.log(data);
				if ("success" == status) {
					if (1 == data) {
						var itemCount = $("#noOfitems").val();

						x = parseInt(itemCount) - parseInt(quantity);
						$("#noOfitems").val(x);
						$("#" + id).remove();
						$("#grandTotal").html(x);
						console.log($("#noOfitems").val());
					}
				}
			});

		}
		function updateQuantity(id, price, itemId) {
			var grandTotal = $("#grandTotal").html();
			var total = $("#total_" + id).html();
			var key = $("#id_" + id).val();

			var quantity = $("#qty_" + id).val();
			total = total.substring(1, total.length);
			grandTotal = parseInt(grandTotal) - parseInt(total);
			var x = parseInt(price) * parseInt(quantity);
			grandTotal = parseInt(grandTotal) + parseInt(x);
			$.post('UpdateCartServlet', {
				'itemId' : itemId,
				'quantity' : quantity
			}, function(data, status) {
				if ("success" == status) {
					if (1 == data[0]) {

						$("#total_" + id).html('$' + x);
						$("#grandTotal").html(grandTotal);
						$("#noOfitems").val(data[1]);
					}
				}
			});
		
		}
	</script>

</body>
</html>
