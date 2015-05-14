<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pending Payment Summary</title>
</head>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<!-- <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" /> -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css"/>
<script	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.js"></script>

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
  
<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>

<!-- Table tools -->
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/tabletools/2.2.1/js/dataTables.tableTools.min.js"></script>
<!--  <link rel="stylesheet" type="text/css"	href="//cdn.datatables.net/tabletools/2.2.1/css/dataTables.tableTools.css"/>
 --> 
<link rel="stylesheet" type="text/css" href="resources/css/common.css"/>
<link rel="stylesheet" type="text/css"	href="resources/css/displayTag.css"/>
<link rel="stylesheet" type="text/css"	href="resources/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css"	href="resources/css/dataTables.tableTools.css"/>
	
<style type="text/css">
#titleDiv {
	float: none;
	left: 750px;
	position: absolute;
	top: 140px;
	   color: black;
	text-decoration: underline;
	font-size: 20px;
	font-weight: bold;
}

#dataDiv {
	float: none;
	left: 450px;
	position: absolute;
	top: 200px;
	width: 800px
}

#btn {
	background: none repeat scroll 0 0 #005555;
    color: #FFFFFF;
    cursor: pointer;
    float: right;
    left: 550px;
    margin-top: 5px;
    padding-bottom: 5px;
    padding-top: 5px;
    position: absolute;
    text-align: center;
    top: 2px;
    width: 100px;
}

#btn:hover {
	color: #333;
	background: none;
}

.dateInputClass {
	height: 25px;
	width: 150px;
}

.plabelClass {
	color: #990099;
	display: inline-block;
	font-size: 13px;
	font-weight: bold;
	line-height: 25px;
	margin-left: 10px;
	padding: 8px;
	width: 75px;
}

#reportDiv {
	 height: 400px;
    margin-top: 30px;
    overflow: auto
}
</style>
<script type="text/javascript">
	$(function() {
		$("#from").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 3,
			onClose : function(selectedDate) {
				$("#to").datepicker("option", "minDate", selectedDate);
			}
		});
		$("#to").datepicker({
			defaultDate : "+1w",
			changeMonth : true,
			numberOfMonths : 3,
			onClose : function(selectedDate) {
				$("#from").datepicker("option", "maxDate", selectedDate);
			}
		});
	});
	$(document).ready(function() {
	    $('#table_id').DataTable( {
	    	"bJQueryUI": true,
	    	"sDom": 'T<"clear"><"fg-toolbar ui-widget-header ui-corner-tl ui-corner-tr ui-helper-clearfix"lfr>t<"fg-toolbar ui-widget-header ui-corner-bl ui-corner-br ui-helper-clearfix"ip>',	    	
	        tableTools: {
	        	 "aButtons": [
	        	              
	        	                {
	        	                    "sExtends": "pdf",
	        	                    "sButtonText": ""
	        	                },
	        	                {
	        	                    "sExtends": "xls",
	        	                    "sButtonText": ""
	        	                }
	        	            ],
	       		 "sSwfPath": "resources/swf/copy_csv_xls_pdf.swf"	
	        }
	    } );
	} );
</script>
<body>
	<jsp:include page="header.jsp" />
	<div id="content">
		<jsp:include page="menu.jsp" />
		<div id="titleDiv">Pending Payment Summary</div>
		<div id="dataDiv">
			<form:form action="pendingsummary" method="post" modelAttribute="dateRange">
				<form:errors path="*" cssClass="errorblock" element="div" />
				<label class="plabelClass" for="from">Start Date</label>
				<form:input path="startDate" id="from" cssClass="dateInputClass" />
				<label class="plabelClass" for="to">End Date</label>
				<form:input path="endDate" id="to" cssClass="dateInputClass" />
				<label for="submit" id="btn">Submit</label>
				<input id="submit" type="submit" />

			</form:form>
			<div id="reportDiv">
				<c:if test="${!empty report.pendingSummaryList}">
				
				<table id="table_id" class="display" >
						<thead>
							<tr>
								
								<c:if test="${user.role.roleName eq 'manager'}">
									<th>Team Lead Name</th>
								</c:if>
								<c:if test="${user.role.roleName eq 'teamlead'}">
							<th>Agent Name</th>
						</c:if>
						<th>Total Pending Amount</th>
						</tr>
						</thead>
						<c:forEach items="${report.pendingSummaryList}" var="reportRow">
						<tr>
						<c:if test="${user.role.roleName eq 'manager'}">
						
							<td>
								<c:out value="${reportRow.managerName}" />
							</td>
						</c:if>
						<c:if test="${user.role.roleName eq 'teamlead'}">
							<td>
								<c:out value="${reportRow.creatorName}" />
							</td>
						</c:if>
						<td >
							<c:out value="${reportRow.pendingAmount}" />
						</td>
						
						</tr>
						</c:forEach>
					</table>
					<%-- <display:table list="${report.pendingSummaryList}" id="reportRow" export="true"
						htmlId="displayTagTable" requestURI="" sort="list" cellpadding="0" cellspacing="0" class="displayTag">						
						<display:column title="Sr No">
							<c:out value="${reportRow_rowNum}"  />
						</display:column>
						<c:if test="${user.role.roleName eq 'manager'}">
							<display:column title="Team Lead Name" group="1" >
								<c:out value="${reportRow.managerName}" />
							</display:column>
						</c:if>
						<c:if test="${user.role.roleName eq 'teamlead'}">
							<display:column title="Agent Name" >
								<c:out value="${reportRow.creatorName}" />
							</display:column>
						</c:if>
						<display:column title="Total Pending Amount" >
							<c:out value="${reportRow.pendingAmount}" />
						</display:column>
						
						<display:setProperty name="export.csv" value="false" />
						<display:setProperty name="export.excel" value="true" />
						<display:setProperty name="export.xml" value="false" />
						<display:setProperty name="export.excel.class"
							value="org.displaytag.export.ExcelView" />
						<display:setProperty name="export.excel.include_header"
							value="true" />
							
						<display:setProperty name="export.excel.filename"
							value="data.xls" />
	<display:setProperty name="export.excel.export_amount"
							value="list" />
					</display:table> --%>
				</c:if>
			</div>
		</div>
	</div>
	
</body>
</html>