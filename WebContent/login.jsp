<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Login Page</title>
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
		if (loggedIn == null) {
			loggedIn = "";
		}
	%>
	<h1 style="color: green">Login</h1>
	<br/>

	<p id='msg'><%=msg%></p>
	<%
		if (!loggedIn.equalsIgnoreCase("true")) {
	%>
	<div class="col-md-8 col-md-offset-1 col-lg-6 col-lg-offset-2">

		<form action='LoginServlet' class="form-horizontal" method='get'>
			<table class="table table-hover">
				<tr>
					<td>Username :</td>
					<td><input class="form-control" type='text' id="username"
						name='username' size='16' maxlength='16' /></td>
				</tr>
				<tr>
					<td><lable class="control-label">Password :</lable></td>
					<td><input class="form-control" type='password'
						name='password' id="password" size='16' maxlength='16' /></td>
				</tr>
				<tr>
					<td colspan="3"><a class="btn btn-primary" onclick="login()">Login</a></td>
				</tr>
			</table>
		</form>
	</div>
	<%
		} else {
	%>
	<p>you are currently logged in</p>
	
	<form action='Logout' method='post'>
		<input type='submit' class="btn btn-toolbar" value='Logout'
			name='Logout' />
	</form>
	<%
		}
	%>

	<script type="text/javascript">
		function login() {
		$.post("LoginServlet", {
				'username' : $("#username").val(),
				'password' : $("#password").val()
			}, function() {
				window.top.location.reload();

			});
		}
	</script>
</body>
</html>
