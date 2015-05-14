<%@page import="com.model.Seller"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %> --%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Login Page</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.min.css" />
</head>

<body>
	<%
		String msg = (String) request.getAttribute("msg");
		if (msg == null) {
			msg = "";
		}
		String loggedIn = (String) session.getAttribute("loggedIn");
		String username = (String) request.getAttribute("userName");
		String other = (String) request.getAttribute("other");
		if (username == null) {
			username = "";
		}

		if (loggedIn == null) {
			out.println("You are not authorized to view this page.");
		} else {
			Seller user = (Seller) session.getAttribute("user");
	%>


	<%
		if (loggedIn.equalsIgnoreCase("true") && null!=user) {
	%>
	<div class="col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
		<h1 style="color: darkgray">Account Information</h1>
		<br>

			
			<p id="msg"><%=msg%></p>
			
			<br>
			<table class="table table-hover">
				<tr>
					<td><label class="label-control">UserName: </label></td>
					<td><%=user.getUserName()%></td>
					<td><label class="label-control">Password: </label></td>
					<td><input type='password' class="label-control"
						name='password' id="password" disabled="disabled"
						value="<%=user.getPassword()%>" /></td>
				</tr>
				<tr>
					<td><label class="label-control">First Name:</label></td>
					<td><%=user.getFirstName()%></td>
					<td><label class="label-control">Last Name:</label></td>
					<td><%=user.getLastName()%></td>

				</tr>
				<tr>
					<td><label class="label-control">Email address:</label></td>
					<td><%=user.getEmail()%></td>
					<td><label class="label-control">Phone number:</label></td>
					<td><%=user.getPhoneNo()%></td>

				</tr>
				<tr>
					<td><label class="label-control">Address:</label></td>
					<td><div>
							<%=user.getAddress().getAddressLine1()%><br /><%=user.getAddress().getAddressLine2()%>
							<br /><%=user.getAddress().getStreet()%>,<%=user.getAddress().getCity()%>,<%=user.getAddress().getState()%><br />
							<%=user.getAddress().getCountry()%>,<%=user.getAddress().getZipcode()%>,
						</div></td>
					<td><label class="label-control">Company Name:</label></td>
					<td><%=user.getCompany()%></td>
				</tr>


				<tr>
					<td colspan="3"><input
						class="btn btn-lg btn-default pull-right" type='button'
						value='Close Account' onclick="return closeAccount();"/></td>
					<td></td>

				</tr>
			</table>
		
	</div>
	<%
		}
		}
	%>
</body>
<script type="text/javascript">
	function closeAccount(){
	  if(confirm('Are you sure?')){
			$.post('AccountCloseServlet',function(data,status){

				if('success'==status){
					if('0'!=data){
						$('#body').load("login.jsp");
						 $('#msg').html('<h5 style="color:red">Failed to close th account</h5>');
					}else{
					 $('#msg').html('<h5 style="color:red">Failed to close th account</h5>');
					}

				}				
			});
	  }
	  return false;
	}
</script>
</html>
