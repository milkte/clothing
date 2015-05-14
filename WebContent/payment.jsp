<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment Method</title>
 <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
 <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
 <script src="scripts/jquery-2.1.3.min.js"></script> 
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
            String cartIdStr =request.getParameter("cartId");
            int cartId = Integer.parseInt(cartIdStr);
            User user = (User)session.getAttribute("user");
        %>
       
            <div class="col-md-8 col-md-offset-1 col-lg-8 col-lg-offset-1" id="paymentmethod">
                    <h1 style="color:darkgray; alignment-adjust: central">Payment Method</h1><br/>
                    <p><%=msg %></p>
				<form action="OrderServlet" method="post">
				<input type="hidden" id='cartId' name='cartId' value="<%=cartId %>">
				<input type="hidden" id='add' name='add' value="0">
				
				<table class="table table-hover">
				<tr><td>Name :</td>+
                    <td><input type='text' class="form-control" id='name' name='name'  maxlength='45' value="<%=user.getFirstName()+" "+user.getFirstName()%>"/></td>
             	</tr>
                <tr><td>Card No: :</td>
                <td><input type='text' class="form-control" name='cardNo'  id='cardNo' maxlength='16'/></td></tr>               
                <tr><td>Email address :</td><td><input type='text' class="form-control" name='email'  id="email" maxlength='45' value="<%=user.getEmail()%>"/></td></tr>
                <tr><td>Phone number :</td><td><input type='text' class="form-control" name='phno'  id="phno"  maxlength='45'  value="<%=user.getPhoneNo()%>"/></td></tr>
                <tr><td>Street :</td><td><input type='text' class="form-control" name='street' id="street"  maxlength='45'  value="<%=user.getAddress().getStreet()%>"/></td></tr>
                <tr><td>City :</td><td><input type='text' class="form-control" name='city' id="city" maxlength='45'  value="<%=user.getAddress().getCity()%>"/></td></tr>
                <tr><td>State :</td><td><input type='text' class="form-control" name='state' id="state" maxlength='45'  value="<%=user.getAddress().getState()%>"/></td></tr>
                <tr><td>Country :</td><td><input type='text' class="form-control" name='country' id="country" maxlength='45'  value="<%=user.getAddress().getCountry()%>"/></td></tr>
                <tr><td>Pincode :</td><td><input type='text'  class="form-control" name='pincode' id="pincode" maxlength='45'  value="<%=user.getAddress().getZipcode()%>"/></td></tr>
                 <tr>
					<td>Card Type:</td>
					<td><input type="radio" name="payment" checked="checked" value="Debit" title="Debit">Debit &nbsp;&nbsp;&nbsp;<input type="radio" name="payment" value="Credit">Credit </td>
				</tr>
				
                <tr><td colspan="3"><input class="btn btn-lg btn-default pull-right" type='submit' value='Submit' onclick="return validate();" /></td></tr></table>
              
	</form>
	</div>
<script type="text/javascript">
	function validate(){
		var error='';
		var name=$("#name").val();
		console.log(name);
		if(''==$("#name").val()){
			error+="Name cannot be empt.\n";
		}
		if(''==$("#cardNo").val()){
			error+="Card No cannot be empt.\n";
		}
		if(''==$("#email").val()){
			error+="Email cannot be empt.\n";
		}
		if(''==$("#phno").val()){
			error+="Phone Number cannot be empt.\n";
		}
		if(''==$("#street").val()){
			error+="Street cannot be empt.\n";
		}
		if(''==$("#city").val()){
			error+="City cannot be empt.\n";
		}
		if(''==$("#state").val()){
			error+="State cannot be empt.\n";
		}
		if(''==$("#country").val()){
			error+="Country cannot be empt.\n";
		}
		if(''==$("#pincode").val()){
			error+="Pincode cannot be empt.\n";
		}
		if(''!=error){
			alert(error);	
			return false;	
		}
		return true;
		
	}
</script>
</body>
</html>