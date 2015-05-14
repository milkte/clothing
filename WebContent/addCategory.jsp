
<%@page import="java.util.LinkedList"%>
<%@page import="com.model.Style"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
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
	%>
	<h1 style="color: green">Add Category</h1>
	</hr>
	<p dir="rtl">
		<a href="index.jsp">Home</a> <a href="/LoginWeb/nav.jsp">Navigation</a>
		<%
			if (loggedIn == null) {
		                    loggedIn = "";
		%>
		<a href="/Clothing/LoginServlet">Login</a>
	</p>
	<br>
	<%
		}
	           List<Category> categories = (List<Category>) application.getAttribute("categories");
	//            if (categories != null) {
	//
	//                for (Category category : categories) {
	%>

	<br>
	<br>
	<p><%=msg%></p>
	<!--<h5 style="color:red">Could not add category. Categoryname is null</h5>-->
	<form action="AddCategory" method="post">
		<input type="hidden" name="noOfStyles" id="noOfStyles" />
		<div class="table col-md-8 col-md-offset-1">
			<div class="row form-group">
				<div class="col-md-2">
					<label class="control-label">Category Name:</label>
				</div>
				<div class="col-md-6">
					<input class="form-control" placeholder="name" type="text"
						name="name">
				</div>
			</div>
			<div class=" row form-group">

				<div class="col-md-2">
					<label class="control-label">Category description:</label>
				</div>
				<div class="col-md-6">
					<input type="text" class="form-control" placeholder="description"
						name="description">
				</div>
			</div>
			<div class="row form-group">
				<div class="col-md-2">
					<label class="control-label">Parent category</label>
				</div>
				<div class="col-md-6">
					<select class="form-control" name="parentCategory">
						<option value=null>None</option>
						<%
							for (Category category : categories) {
						%>
						<option value="<%=category.getId()%>">
							<%=category.getCategoryName()%></option>
						<%
							}
						%>
					</select>
				</div>

			</div>

			<div class="row">
				<div class="col-md-2">

					<label class="label">Style </label>
				</div>
				<div id="styleDiv" class="col-md-6">
					<div id="error" style='color: red'>Enter Style name</div>
					<div id="first" class="row form-group">
						<div class="col-md-6">
							<input type="text" onfocus="clearError()"
								onblur="checkInput('styleName_0')" id="styleName_0"
								placeholder="Style Name" class="form-control" name="styleName_0">
						</div>
						<div class="col-md-1">
							<input type="button" value=" + " class="myclass" id="addButton"
								name="add" onclick="addRow()">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 pull-right">
						<input type="submit">
					</div>
				</div>
			</div>
		</div>


	</form>
	<script>
		$(document).ready(function() {
			$("#noOfStyles").val("0");
			$("#error").hide();

		});

		function clearError() {
			$("#error").hide();
		}
		function checkInput(id) {

			if (!($("#" + id).val())) {
				$("#error").show();
				return false;
			} else {
				$("#error").hide();
				return true;
			}
		}
		function addRow() {
			var no = parseInt($("#noOfStyles").val(), 10);

			if (!checkInput("styleName_" + (no))) {
				$("#styleDiv").append("");
				return;
			}
			no = no + 1;
			var name = "styleName_" + no;

			$("#noOfStyles").val("" + no);
			var divSubmit = $(document.createElement('div'));
			divSubmit.attr("class", "row form-group");
			//                console.log("created");

			$(divSubmit)
					.append(
							'<div class = "col-md-6" > <input onfocus="clearError()"  type = "text" placeholder = "Style Name" onblur="checkInput(\''
									+ name
									+ '\')" id="'
									+ name
									+ '" class = "form-control" name="'
									+ name
									+ '" > </div>  <div class = "col-md-1" > <input type = "button" value = " + " class="myclass" name="add"  onclick="addRow()"> </div> </div>');
			//                console.log("appended");
			$("#styleDiv").append(divSubmit);
			//                add(divSubmit);
			//                console.log("added");
		};
	</script>
</body>
</html>
