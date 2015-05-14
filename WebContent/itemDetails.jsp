<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link href="fonts/glyphicons-halflings-regular.ttf" />
<link href="fonts/glyphicons-halflings-regular.woff" />
<link href="css/star-rating.min.css" media="all" rel="stylesheet"
	type="text/css" />
<script src="scripts/jquery-2.1.3.min.js"></script>
<script src="scripts/star-rating.min.js" type="text/javascript"></script>

<body>
	<div
		class="col-xs-12 col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1"
		id="itemDetails">
		<h1>Item Details</h1>

		<div>

			<div class="row">
				<div class="col-xs-3 col-md-2 col-lg-2">
					<label class="control-label">Item Name:</label>
				</div>
				<div class="col-xs-3 col-md-4 col-lg-4">
					<label class="control-label" id="itemName"> </label>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3 col-md-2 col-lg-2">
					<label class="control-label"> Price:</label>
				</div>
				<div class="col-xs-3 col-md-4 col-lg-4">
					<label class="control-label" id="price"> </label>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3 col-md-2 col-lg-2">
					<label class="control-label">Style:</label>
				</div>
				<div class="col-xs-3 col-md-4 col-lg-4">
					<label class="control-label" id="style"> </label>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-3 col-md-2 col-lg-2">
					<label class="control-label">Options:</label>
				</div>
				<div class="col-xs-9 col-md-8" id="detailsDiv">
					<div class="row"></div>
				</div>

			</div>
			<div class="col-xs-12 col-md-12 col-lg-12">
				<div class="col-xs-3 col-md-2 col-lg-2">
					<label class="control-label">User Ratings</label>
				</div>
				<div id="ratings"></div>
			</div>
		</div>
	</div>
	
</body>
<script type="text/javascript">
	function onAddToCart(id,colorId,sizeId) {
		$.get('AddItemToCartServlet', {
			'itemId' : id,
			'colorId' : colorId,
			'sizeId' : sizeId
		}, function(data) {
			
		});
	}
	function sendMessage(){
		}
</script>
</html>