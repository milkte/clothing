<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Seller Login Page</title>
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
<script src="scripts/jquery-2.1.3.min.js"></script>
<script src="scripts/bootstrap/bootstrap.min.js"></script>

<!-- Latest compiled and minified CSS -->

<script src="scripts/jquery.validate.min.js"></script>

</head>
<style>
.error {
	color: red;
}

</style>
<script type="text/javascript">
	$(document).ready(function() {
	$('#body').css('left','100px');
	$('#body').css('top','700px');
		$("#sellersignup").validate({
	rules : {
				username : {
					required : true
					

				},
				password : "required",
				conpassword : {
					equalTo : "#password"
				},
				email : {
					required : true,
					email : true
				},
				phno : {
					required : true,
					isValidPhone:true
					
				},
				pincode : {
					required : true,
					VPN:true
					
				},
				account : {
					required : true,
					VAN:true
					
				},
				routing : {
					required : true,
					VRN:true
					
				}

			},
	       
			messages : {
				username : {
					required : 'User Name is required',
					isUserNameValid : 'User Name is not available'
				},
				conpassword:{
					equalTo:'Not matching with password'
					},
				email : {
					required : 'Email is required',
					email : 'Email is in correct format'
				}				

			}
		});
	});
	  
	jQuery.validator.addMethod("isValidPhone", function (value, element) {
        return validatePhone(value)
    }, "Please specify valid phone number");

    function validatePhone(number) {
        debugger;
        var regex = new RegExp("^[0-9]{3}-[0-9]{3}-[0-9]{4}");
        if (!regex.test(number))
            return false;

        return true;
    }
    jQuery.validator.addMethod("VAN", function (value, element) {
        return validateAccNo(value)
    }, "Please specify valid account number");

    function validateAccNo(number) {
        debugger;
        var regex = new RegExp("^[0-9]{12}");
        if (!regex.test(number))
            return false;

        return true;
    }
    jQuery.validator.addMethod("VRN", function (value, element) {
        return validateRoutingNo(value)
    }, "Please specify valid routing number");

    function validateRoutingNo(number) {
        debugger;
        var regex = new RegExp("^[0-9]{9}");
        if (!regex.test(number))
            return false;

        return true;
    }
    jQuery.validator.addMethod("VPN", function (value, element) {
        return validatePinCode(value)
    }, "Please specify valid pincode number");

    function validatePinCode(number) {
        debugger;
        var regex = new RegExp("^[0-9]{5}(?:-[0-9]{4})?$");
        if (!regex.test(number))
            return false;

        return true;
    }
    jQuery.validator.addMethod("isUserNameValid", function (value, element) {
        return validateUserName1(value)
    }, "User name is not available");
    
	function validateUserName1() {

		var username = $('#username').val();
		$.get('SellerSignupServlet', {
			'validate' : 'validate',
			'username' : username
		}, function(data, status) {
			if ('success' == status) {
				return data;
			}
		});

	}
</script>

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

	<div class="col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1" style="overflow: auto;">
	<p><%=msg%></p>
		<h1 style="color: darkgray">Join us as Seller</h1>
		<br/>
		<form action='SellerSignupServlet' method='post' id="sellersignup">
			<table class="table table-hover">
				<tr>
					<td><label class="label-control">UserName <font
							color="red">*</font>:
					</label></td>
					<td><input type='text' class="form-control"
						class="form-control" name='username' value="<%=username%>"
						maxlength='45' id="username" required="required" /></td>
					<td><input class="btn btn-toolbar" type='button'
						value='validate' name='validate' onclick="validateUserName();" /></td>
						<td id="usenamemsg"></td>
				</tr>
				<tr>
					<td><label class="label-control">First Name <font
							color="red">*</font>:
					</label></td>
					<td><input type='text' class="form-control" name='fname'
						maxlength='45' id="fname" required="required" /></td>
				
					<td><label class="label-control">Last Name <font
							color="red">*</font>:
					</label></td>
					<td><input type='text' class="form-control" name='lname'
						maxlength='45' id="lname" required="required" /></td>
				</tr>
				<tr>
					<td><label class="label-control">Password <font
							color="red">*</font>:
					</label></td>
					<td><input type='password' class="form-control"
						name='password' maxlength='45' id="password" required="required" /></td>
				
					<td><label class="label-control">Confirm Password <font
							color="red">*</font>:
					</label></td>
					<td><input type='password' class="form-control"
						name='conpassword' maxlength='45' id="conpassword"
						required="required" /></td>
				</tr>
				<tr>
					<td><label class="label-control">Email address <font
							color="red">*</font>:
					</label></td>
					<td><input type="email" class="form-control" name='email'
						maxlength='45' placeholder="email@example.com"
						pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
						id="email" required="required" /></td>
				
					<td><label class="label-control">Phone number <font
							color="red">*</font>:
					</label></td>
					<td><input type="tel" class="form-control" name='phno'
						maxlength='45' placeholder="999-999-9999"
						pattern="^[0-9]{3}-[0-9]{3}-[0-9]{4}" id="phno"
						required="required" /></td>
				</tr>
				<tr>
					<td><label class="label-control">Address Line 1 <font
							color="red">*</font>:
					</label></td>
					<td><input type="text" class="form-control" name='addressl1'
						maxlength='45'id="addressl1" required="required" /></td>
				
					<td><label class="label-control">Address Line 2<font
							color="red">*</font>:
					</label></td>
					<td><input type="text" class="form-control" name='addressl2'
						maxlength='45'id="addressl2" /></td>
				</tr>
				<tr>
					<td><label class="label-control">Street <font
							color="red">*</font>:
					</label></td>
					<td><input type='text' class="form-control" name='street'
						maxlength='45' id="street" required="required" /></td>
					<td><label class="label-control">City <font
							color="red">*</font>:
					</label></td>
					<td><input type='text' class="form-control" name='city'
						maxlength='45' id="city" required="required" /></td>

				</tr>
				<tr>
				<td><label class="label-control">State :</label></td>

					<td><Select name="state" class="form-control">
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
					<td><label class="label-control">Country :</label></td>
					<td><input type='text' class="form-control" name='country'
						maxlength='45' value="USA" disabled="disabled"
						required="required"></td>			
					
				</tr>
				<tr>
				<td><label class="label-control">Pincode :</label></td>
					<td><input type="text" class="form-control" name='pincode'
						maxlength='5' placeholder="12345"
						pattern="^[0-9]{5}(?:-[0-9]{4})?$" required="required" /></td>
					<td><label class="label-control">Company Name: <font
							color="red">*</font></label></td>
					<td><input type="text" name="cName" class="form-control"
						required="required"></td>
				
					
				</tr>
				<tr>
				<td><label class="label-control">Account Number: <font
							color="red">*</font></label></td>
					<td><input type="text" name="account" class="form-control"
						maxlength="12" placeholder="0112345678999" pattern="^[0-9]{12}"
						required="required"></td>
					<td><label class="label-control">Routing Number: <font
							color="red">*</font></label></td>
					<td><input type="text" name="routing" class="form-control"
						maxlength="9" placeholder="123456789" pattern="^[0-9]{9}"
						required="required" onsubmit="return validate();"></td><td></td><td></td>
				</tr>
				<tr>
					<td colspan="3"><input
						class="btn btn-lg btn-default pull-right" type='submit'
						value='Sign Up' /></td>
						<td></td>
						
				</tr>
			</table>
		</form>
		<div id="dialog"></div>
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
