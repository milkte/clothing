<%-- 
    Document   : admins
    Created on : 20 Apr, 2015, 11:00:53 PM
    Author     : shruti
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>
        <script src="scripts/jquery-2.1.3.min.js"></script> 

    </head>
    <body>
        <div class="row">

            <div  class="col-xs-10  col-md-10 col-lg-10">
                <h1 style="color:darkgray" class="col-xs-12 col-md-12 col-lg-12">Admin management</h1>
                <div id="message" class="row">


                </div>
                <br>
                <form>
                    <h3 class="col-xs-12  col-md-12 col-lg-12">Add admin</h3>
                    <div class="col-xs-10 col-md-10 col-lg-10 form-group">
                        
                        <div class="col-xs-5 col-md-5 input-group">
                            <span class="input-group-addon glyphicon glyphicon-search"></span>
                            <input type="search" placeholder="Search user" class="form-control" onkeypress="searchUser()" id="search">
                        </div>
                       
                    </div>
                    <!--<h3 class="row">Search result</h3>-->
                    <div id="searchResult" class="col-xs-10  col-md-10 col-lg-10 ">

                    </div>
                </form>

                <h3 class="col-xs-12 col-md-12 col-lg-12">Existing admins</h3>
                <form class="form-horizontal">
                    <div id="adminrows" class="col-xs-12  col-md-12 col-lg-12">
                        <div class="row">
                            <div class="col-xs-4 col-md-2 col-lg-2"><label class="control-label">User name</label></div>
                            <div class="col-xs-4 col-md-2 col-lg-2"><label class="control-label">Email address</label></div>
                            <div class="col-xs-3 col-md-2 col-lg-2"><label class="control-label">Phone number</label></div>
                            <div class="col-xs-1 col-md-2 col-lg-2"><label class="control-label">Action</label></div>
                        </div>
                        <!--<div class="row"><div class="col-xs-2 col-md-2 col-lg-2">User name</div><div class="col-xs-2 col-md-2 col-lg-2">Email address</div><div class="col-xs-2 col-md-2 col-lg-2">Phone number</div><div class="col-xs-2 col-md-2 col-lg-2"><button class="btn btn-toolbar"><span class="glyphicon glyphicon-ok"></span></button></div></div>-->
                    </div>
                </form>
            </div>
        </div>
        <script>
            function searchUser() {
                var keyword = $("#search").val();
                $.get('EmployeeServlet', {'getUserLike': 'getUserLike', 'keyword': keyword}, function(data) {
                    $("#searchResult").empty();
                    $.each(data, function(key, value) {
                        alert(value.firstName);
                        $("#searchResult").append('<div class="row"><div class="col-xs-4 col-md-2 col-lg-2">' + value.firstName + " " + value.lastName + '</div><div class="col-xs-4 col-md-2 col-lg-2">' + value.email + '</div><div class="col-xs-3 col-md-2 col-lg-2">' + value.phoneNo + '</div><div class="col-xs-1 col-md-2 col-lg-2"><button class="btn btn-toolbar" onclick="addAdmin('+value.userId+')"><span class="glyphicon glyphicon-ok"></span></button></div></div>');
                    });
                });
            }
            ;
            function addAdmin(id) {
                $("#message").empty();
                $.post('AdminServlet', {'add': 'admin', 'user': id}, function(data) {
                    $("#message").append('<p class="bg-primary">Admin added successfully</p>');
                }, function() {
                    $("#message").append('<p class="bg-danger">Admin could not be added.</p>');
                });
            };
            function removeAdmin(id) {
                
                $("#message").empty();
                $.post('AdminServlet', {'remove': 'admin', 'user': id}, function() {
                    $("#message").append('<p class="bg-primary">Admin removed successfully</p>');
                }, function() {
                    $("#message").append('<p class="bg-danger">Admin could not be removed.</p>');
                });
            };
            function loadAdmins(){
                  $.get("AdminServlet", {'get': 'admins'}, function(data) {
                    $.each(data, function(key, value) {
                        $("#adminrows").append('<div class="row"><div class="col-xs-4 col-md-2 col-lg-2">' + value.firstName + " " + value.lastName + '</div><div class="col-xs-4 col-md-2 col-lg-2">' + value.email + '</div><div class="col-xs-3 col-md-2 col-lg-2">' + value.phoneNo + '</div><div class="col-xs-1 col-md-2 col-lg-2"><button class="btn btn-toolbar"  onclick="removeAdmin('+value.userId+')"><span class="glyphicon glyphicon-minus-sign" ></span></button></div></div>');
                    });

                });
            };
            $(document).ready(function() {
              loadAdmins();
            });
        </script>
    </body>
</html>
