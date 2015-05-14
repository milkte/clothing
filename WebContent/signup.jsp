<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Seller Sign up Page</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />

</head>

<body>
	<%
		String msg = (String) request.getAttribute("msg");
		if (msg == null) {
			msg = "";
		}
		String loggedIn = (String) session.getAttribute("loggedIn");
		String username = (String) request.getAttribute("userName");
		if (username == null) {
			username = "";
		}
		if (loggedIn == null) {
			loggedIn = "";
		}
	%>

	<%
		if (!loggedIn.equalsIgnoreCase("true")) {
	%>
	<div class="col-md-8 col-md-offset-1 col-lg-8 col-lg-offset-1">
		<h1 style="color: darkgray; alignment-adjust: central">Sign Up</h1>
		<br/>
		<p><%=msg%></p>
		<br>
		<form action='SignupServlet' method='post'>
			<table class="table table-hover">
				<tr>
					<td>UserName <font color="red">*</font>:
					</td>
					<td><input type='text' class="form-control"
						class="form-control" name='username' value="<%=username%>"
						maxlength='45' /></td>
					<td><input class="btn btn-toolbar" type='submit'
						value='validate' name='validate' /></td>
				</tr>
				<tr>
					<td>First Name <font color="red">*</font>:
					</td>
					<td><input type='text' class="form-control" name='fname'
						maxlength='45' /></td>
				</tr>
				<tr>
					<td>Last Name <font color="red">*</font>:
					</td>
					<td><input type='text' class="form-control" name='lname'
						maxlength='45' /></td>
				</tr>
				<tr>
					<td>Password <font color="red">*</font>:
					</td>
					<td><input type='password' class="form-control"
						name='password' maxlength='45' /></td>
				</tr>
				<tr>
					<td>Confirm Password <font color="red">*</font>:
					</td>
					<td><input type='password' class="form-control"
						name='conpassword' maxlength='45' /></td>
				</tr>
				<tr>
					<td>Email address <font color="red">*</font>:
					</td>
					<td><input type='text' class="form-control" name='email'
						maxlength='45' placeholder="email@example.com" /></td>
				</tr>
				<tr>
					<td>Phone number <font color="red">*</font>:
					</td>
					<td><input type='text' class="form-control" name='phno'
						maxlength='45' placeholder="999-999-9999" /></td>
				</tr>
				<tr>
					<td>City :</td>
					<td><input type='text' class="form-control" name='city'
						maxlength='45' /></td>
				</tr>
				<tr>
					<td>State :</td>
					<td><td>
						<Select name="state">
							<option value="AL">Alabama</option>
							<option value="AK">Alaska</option>
							<option value="AZ">Arizona</option>
							<option value="AR">Arkansas</option>
							<option value="CA">California</option>
							<option value="CO">Colorado</option>
							<option value="CT">Connecticut</option>
							<option value="DE">Delaware</option>
							<option value="DC">District Of Columbia</option>
							<option value="FL">Florida</option>
							<option value="GA">Georgia</option>
							<option value="HI">Hawaii</option>
							<option value="ID">Idaho</option>
							<option value="IL">Illinois</option>
							<option value="IN">Indiana</option>
							<option value="IA">Iowa</option>
							<option value="KS">Kansas</option>
							<option value="KY">Kentucky</option>
							<option value="LA">Louisiana</option>
							<option value="ME">Maine</option>
							<option value="MD">Maryland</option>
							<option value="MA">Massachusetts</option>
							<option value="MI">Michigan</option>
							<option value="MN">Minnesota</option>
							<option value="MS">Mississippi</option>
							<option value="MO">Missouri</option>
							<option value="MT">Montana</option>
							<option value="NE">Nebraska</option>
							<option value="NV">Nevada</option>
							<option value="NH">New Hampshire</option>
							<option value="NJ">New Jersey</option>
							<option value="NM">New Mexico</option>
							<option value="NY">New York</option>
							<option value="NC">North Carolina</option>
							<option value="ND">North Dakota</option>
							<option value="OH">Ohio</option>
							<option value="OK">Oklahoma</option>
							<option value="OR">Oregon</option>
							<option value="PA">Pennsylvania</option>
							<option value="RI">Rhode Island</option>
							<option value="SC">South Carolina</option>
							<option value="SD">South Dakota</option>
							<option value="TN">Tennessee</option>
							<option value="TX">Texas</option>
							<option value="UT">Utah</option>
							<option value="VT">Vermont</option>
							<option value="VA">Virginia</option>
							<option value="WA">Washington</option>
							<option value="WV">West Virginia</option>
							<option value="WI">Wisconsin</option>
							<option value="WY">Wyoming</option>
					</select></td>
				</tr>
				<tr>
					<td>Country :</td>
					<td><input type='text' class="form-control" name='country'
						maxlength='45'  value="USA" disabled="disabled"/></td>
				</tr>
				<tr>
					<td>Pincode :</td>
					<td><input type='text' class="form-control" name='pincode'
						maxlength='45' /></td>
				</tr>				
				<tr>
					<td colspan="3"><input
						class="btn btn-lg btn-default pull-right" type='submit'
						value='Sign Up'/></td>
				</tr>
			</table>
		</form>
	</div>
	<%
		} else {
	%>
	<p>you are currently logged in</p>
	<form action='Logout' method='post'>
		<input type='submit' value='Logout' name='Logout' />
	</form>
	<%
		}
	%>
</body>
</html>
