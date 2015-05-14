<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="scripts/jquery-2.1.3.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

<script src="scripts/jssor.slider.mini.js"></script>
<script src="scripts/bootstrap/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Authorization</title>
</head>
<body>
<div>
<table id ="sellers" class="table">
</table>

</div>
	<script type="text/javascript">
		
		$(document).ready(function() {
			$('#body').css('left', '100px');
			$('#body').css('overflow', 'auto');
			$.get('AuthorizationServlet', {
				'getSellers' : 'getSellers'
			}, function(data, status) {
				if ("success" == status) {
					$('#sellers').empty();
					 var sellers =JSON.parse(data);
					 console.log(sellers);
					 $('#sellers').append('<thead><tr><td>Name</td><td>Email</td><td>Action</td></tr></thead>');
					 $('#sellers').append('<tbody>');
					 $.each(sellers,function(key,value){
					  var tr='<tr><td>'+value.firstName+" "+value.lastName+'</td><td>'+value.email+'</td>';
					  var action='';
					  if(value.isLocked==1){
						  action='<td><input type="button" id="decline" style="display:none" value="Decline" onclick="decline('+value.userId+');"><input type="button" id="approve" value="Approve" onclick="approve('+value.userId+');"></td>';
							}else{								 
								action='<td><input type="button" id="decline" value="Decline" onclick="decline('+value.userId+');"><input type="button" id="approve" style="display:none" value="Approve" onclick="approve('+value.userId+');"></td>';
						  
						  	 }
					  tr+=action+'</tr>';
					  $('#sellers').append(tr);
					  $('#sellers').append('<tbody>');
					  $('#sellers').css('overflow', 'auto');
					 });
					
				}

			});
		});
		function decline(userId){

			$.get('AuthorizationServlet', {
				'decline' : 'decline',
				'userId' : userId
			}, function(data, status) {
				if ("success" == status) {
						$('#decline').hide();
						$('#approve').show();
				}
			});
		}
		
		function approve(userId) {
			$.get('AuthorizationServlet', {
				'approve' : 'approve',
				'userId' : userId
			}, function(data, status) {
				if ("success" == status) {
					$('#decline').show();
					$('#approve').hide();
				}
			});
		}
	</script>
</body>
</html>