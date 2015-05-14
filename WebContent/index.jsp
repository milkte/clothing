<%@page import="com.dao.AuthDAO"%>
<%@page import="com.dao.CategoryDao"%>
<%@page import="java.util.List"%>
<%@page import="com.model.Category"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html>

    <head>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css" />
		<link rel="stylesheet" href="css/bootstrap/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="css/bootstrap/bootstrap.css" />
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<script src="scripts/jquery-2.1.3.min.js"></script>
       <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
 
        <script src="scripts/jssor.slider.mini.js"></script>
        <script src="scripts/bootstrap/bootstrap.min.js"></script>
        <script type="text/javascript" src="scripts/clothing/categories.js"></script>
        <script type="text/javascript" src="scripts/clothing/index.js"></script>
         <script type="text/javascript" src="scripts/clothing/validation.js"></script>
    </head>
    <body style="background-color: black">


        <div class="col-md-12 col-lg-12">

            <div class="col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1"
                 style="background-color: white">
                <div id='header'>
                    <h1 style="color:blue">Welcome ! </h1><hr>
                    <p dir="rtl">
                        <%	String loggedIn = (String) session.getAttribute("loggedIn");
                            String role = (String) session.getAttribute("role");
                            if (loggedIn != null)
                            out.println("welcome   " + session.getAttribute("username"));
                            
                            //out.println("role" + role);
                            if (loggedIn == null) { %>
                        <a class="btn btn-link" onclick="loadjsp('login.jsp')">Login</a>
                        <a class="btn btn-link" onclick="loadjsp('signup.jsp')">Sign Up</a>
                        <a class="btn btn-link" onclick="loadjsp('signupseller.jsp')">Join As Seller</a>
                        <%} else { %>
                        <a class="btn btn-link" target="_top" href="/Clothing/LogoutServlet">Logout</a>
                        <%} %>
                        <a
                            class="btn btn-link"  onclick="loadjsp('viewcart.jsp')">My
                            cart</a>

                    </p>
                    <div class="container container-fluid col-md-12 col-lg-12"
                         style="size: auto; width: available">
                        <!-- Jssor Slider Begin -->
                        <!-- To move inline styles to css file/block, please specify a class name for each element. -->
                        <!-- ================================================== -->
                        <div id="slider1_container"
                             class="container container-fluid col-md-12 col-ld-12"
                             style="display: none; position: relative; margin: 0 auto; width: 1140px; height: 442px; overflow: hidden;">

                            <!-- Slides Container -->
                            <div u="slides"
                                 style="cursor: move; position: relative; left: 0px; top: 0px; width: 1140px; height: 442px; overflow: hidden;">
                                <div>
                                    <img u="image" src2="images/img0.jpeg" />
                                </div>
                                <div>
                                    <img u="image" src2="images/img1.jpeg" />
                                </div>
                                <div>
                                    <img u="image" src2="images/img3.jpeg" />
                                </div>
                                <div>
                                    <img u="image" src2="images/img13.jpeg" />
                                </div>
                                <div>
                                    <img u="image" src2="images/img14.jpeg" />
                                </div>
                                <div>
                                    <img u="image" src2="images/img9.jpeg" />
                                </div>
                                <div>
                                    <img u="image" src2="images/baby3.jpg" />
                                </div>
                                <div>
                                    <img u="image" src2="images/baby4.jpg" />
                                </div>
                                <div>
                                    <img u="image" src2="images/img32.jpg" />
                                </div>
                            </div>

                            <!--#region Bullet Navigator Skin Begin -->
                            <!-- Help: http://www.jssor.com/development/slider-with-bullet-navigator-jquery.html -->
                            <style>
                                /* jssor slider bullet navigator skin 05 css */
                                /*
                                                                .jssorb05 div           (normal)
                                                                .jssorb05 div:hover     (normal mouseover)
                                                                .jssorb05 .av           (active)
                                                                .jssorb05 .av:hover     (active mouseover)
                                                                .jssorb05 .dn           (mousedown)
                                */
                                .jssorb05 {
                                    position: absolute;
                                }

                                .jssorb05 div, .jssorb05 div:hover, .jssorb05 .av {
                                    position: absolute;
                                    /* size of bullet elment */
                                    width: 16px;
                                    height: 16px;
                                    background: url(images/b05.png) no-repeat;
                                    overflow: hidden;
                                    cursor: pointer;
                                }

                                .jssorb05 div {
                                    background-position: -7px -7px;
                                }

                                .jssorb05 div:hover, .jssorb05 .av:hover {
                                    background-position: -37px -7px;
                                }

                                .jssorb05 .av {
                                    background-position: -67px -7px;
                                }

                                .jssorb05 .dn, .jssorb05 .dn:hover {
                                    background-position: -97px -7px;
                                }
                            </style>
                            <!-- bullet navigator container -->
                            <div u="navigator" class="jssorb05"
                                 style="bottom: 16px; right: 6px;">
                                <!-- bullet navigator item prototype -->
                                <div u="prototype"></div>
                            </div>
                            <!--#endregion Bullet Navigator Skin End -->

                            <!--#region Arrow Navigator Skin Begin -->
                            <!-- Help: http://www.jssor.com/development/slider-with-arrow-navigator-jquery.html -->
                            <style>
                                /* jssor slider arrow navigator skin 11 css */
                                /*
                                                                .jssora11l                  (normal)
                                                                .jssora11r                  (normal)
                                                                .jssora11l:hover            (normal mouseover)
                                                                .jssora11r:hover            (normal mouseover)
                                                                .jssora11l.jssora11ldn      (mousedown)
                                                                .jssora11r.jssora11rdn      (mousedown)
                                */
                                .jssora11l, .jssora11r {
                                    display: block;
                                    position: absolute;
                                    /* size of arrow element */
                                    width: 37px;
                                    height: 37px;
                                    cursor: pointer;
                                    background: url(images/a11.png) no-repeat;
                                    overflow: hidden;
                                }

                                .jssora11l {
                                    background-position: -11px -41px;
                                }

                                .jssora11r {
                                    background-position: -71px -41px;
                                }

                                .jssora11l:hover {
                                    background-position: -131px -41px;
                                }

                                .jssora11r:hover {
                                    background-position: -191px -41px;
                                }

                                .jssora11l.jssora11ldn {
                                    background-position: -251px -41px;
                                }

                                .jssora11r.jssora11rdn {
                                    background-position: -311px -41px;
                                }
                            </style>
                            <!-- Arrow Left -->
                            <span u="arrowleft" class="jssora11l"
                                  style="top: 123px; left: 8px;"> </span>
                            <!-- Arrow Right -->
                            <span u="arrowright" class="jssora11r"
                                  style="top: 123px; right: 8px;"> </span>
                            <!--#endregion Arrow Navigator Skin End -->
                            <a style="display: none" href="http://www.jssor.com">Bootstrap
                                Slider</a>
                        </div>
                        <!-- Jssor Slider End -->
                    </div>

                    <div class="col-md-12 col-lg-12"
                         style="background-color: black; color: whitesmoke">

					<%
						String msg = (String) request.getAttribute("msg");
					                            if (msg == null) {
					                                msg = "";
					                            }

					                            //	out.println("loggedIn" + loggedIn);
					                            //out.println("role" + role);
					                            if (loggedIn == null || (role != null && role.equals(AuthDAO.USER_ROLE))) {
					                                loggedIn = "";

					                                List<Category> categories = null;
					                                categories = new CategoryDao().getRootCategories();
					                                if (categories != null) {
					                                    application.setAttribute("categories", categories);
								for (Category category : categories) {
					%>
					<a class="btn btn-lg btn-toolbar dropdown dropdown-toggle"
						onclick="getCategoryItem(<%=category.getId()%>);"> <%=category.getCategoryName()%></a>
					<%
						}

							}
					                                
					                                if (role != null && role.equals(AuthDAO.USER_ROLE)) {%>
					                                <a class="btn btn-lg btn-toolbar dropdown dropdown-toggle" onclick="loadjsp('buyerOrders.jsp')">Manage Orders</a>
					                               <button type="button" data-placement="bottom" id="notificationBtn"  class="btn btn-lg btn-toolbar pull-right" data-toggle="popover" title="Notifications" ><span  id="notification" class="badge">0</span></button>
                        						<button type="button" class="col-xs-2 col-md-1 col-lg-1 btn btn-primary pull-right" id="msgButton"  data-placement="bottom" data-toggle="popover" title="Message" >Messages<span  id="message" class="badge"></span></button>
                       
					                                <%
					                                	
					                                }
						} else if (role != null && role.equals(AuthDAO.ADMIN)) {
					%>
					<a class="btn btn-lg  dropdown dropdown-toggle"  onclick="loadjsp('admins.jsp')">Manage Admin</a> 
                        <div class="btn-group">
                            <button  class="btn btn-lg btn-default dropdown dropdown-toggle"
                            data-toggle="dropdown" aria-expanded="false">View Database</button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a onclick="loadjsp('sellerItems.jsp')" >Items</a></li>
                            <li class="divider"></li>
                            <li><a onclick="loadjsp('sellerOrders.jsp')" href="">Orders</a></li>
                            
                        </ul>
                            </div>
                        <a
                            class="btn btn-lg btn-toolbar dropdown dropdown-toggle"
                            onclick="loadjsp('EmployeeServlet?isSeller=false&responseUrl=/viewuser.jsp')">View Profile</a>
                            <a
                            class="btn btn-lg btn-toolbar dropdown dropdown-toggle"
                            onclick="loadjsp('authorization.jsp')">User Authorization</a>
                        <a
                            class="btn btn-lg btn-toolbar dropdown dropdown-toggle"
                            href="#">View Profile</a>
                        <button type="button" data-placement="bottom" id="notificationBtn"  class="btn btn-lg btn-toolbar pull-right" data-toggle="popover" title="Notifications" ><span  id="notification" class="badge">0</span></button>
                        <button type="button" class="col-xs-2 col-md-1 col-lg-1 btn btn-primary pull-right" id="msgButton"  data-placement="bottom" data-toggle="popover" title="Message" >Messages<span  id="message" class="badge"></span></button>
                            <%
                            } else if (role != null && role.equals(AuthDAO.SELLER_ROLE)) {
                            %>
                         <a class="btn btn-lg btn-toolbar dropdown dropdown-toggle" onclick="loadjsp('addItem.jsp')">Add item</a>
                         <a class="btn btn-lg btn-toolbar dropdown dropdown-toggle" onclick="loadjsp('sellerItems.jsp')">My Products</a>
                          <a class="btn btn-lg btn-toolbar dropdown dropdown-toggle" onclick="loadjsp('sellerOrders.jsp')">Manage Orders</a> <a
                           class="btn btn-lg btn-toolbar dropdown dropdown-toggle" onclick="loadjsp('viewseller.jsp')">View Profile</a> 
                           
                        <%
                            }
                            
                        %>
                        
                    </div>
                    <%--<%="here"%>--%>

                </div>

                <p><%=msg%></p>
                <!--            <a href="/Clothing/men">Men's Clothing</a>
                    <a href="/Clothing/women">Women's Clothing</a>
                    <a href="/Clothing/kids">Kid's Clothing</a>-->
               <div class="row">
				<div id="sidemenu"
					style="width: 10%; height: 100%; float: left; overflow: auto;">
					<div class="col-xs-12 col-md-12 col-lg-12" id="filter"></div>
					<table class="table table-hover" id="subcat"></table>
				</div>
				<div id="body"
					style="width: 90%; height: 100%; float: right; overflow: auto; top: 750px; position: absolute; left: 200px;">

				</div>
			</div>


<!--                <form action="">
                    <fieldset>
                        <div class="col-xs-6 col-md-4 col-lg-4">
                            <input type="text" class="form-control" id="to">
                        </div>
                        <div class="col-xs-6 col-md-4 col-lg-4">

                            <textarea rows="5" cols="40" placeholder="Enter Message Text" id="msgText" class="form-control" name="msgText"></textarea>
                        </div>
                        <div class="col-xs-6 col-md-4 col-lg-4">
                            <input type="Submit" class="btn btn-default">
                        </div>
                    </fieldset>
                </form>-->
            </div>
        </div>

        <script>
            function showNotification() {
                $("#popover").popover({
                    trigger: "click",
                    placement: 'right',
                    html: true,
                    template: '<div class="popover" id="notification1" title="notification" ><span class="glyphicon glyphicon-globe"></span> </div><div class="popover-title">Nitifications</div><div id="notificationContent1" class="popover-content">...</div>'

                }).popover('show');
            }
            function pollCount() {
//                alert("poll");
                $.ajax({
                    url: 'NotificationServlet',
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    data: {'getCount': 'count'},
                    cache: true,
                    success: function(data) {
                        console.log(data);
                        // $('#output ul').append('<li>The feed loads fine');

                        $('#notification').html(data.notificationCount);
                        $('#message').html(data.messageCount);
//
                    },
                    error: function(data) {
                        console.log("error" + JSON.stringify(data))
                    }
                });
            }

            $(document)
                    .ready(
                            function() {
                               
                                setInterval(pollCount, 30000);
                                $('*[data-poload]').click(function() {
                                    var e = $(this);
                                    $.get(e.data('poload'), function(d) {
                                        e.popover({content: '<div>' + d + '</div>'}).popover('show');
                                    });
                                });
                                $("#popover").popover({
                                    trigger: "click",
                                    placement: 'bottom',
                                    html: true,
                                    template: '<div class="popover"><div class="arrow"></div><div class="popover-inner"><h3 class="popover-title"></h3><div class="popover-content"><p></p></div></div></div>'

                                });
                                $('#notification').click(function() {
                                    $("#notificationContent").append("<div>abcd</div>");
                                });

                                $('[data-toggle="popover"]').click(function() {
                                    var e = $(this);
                                    console.log(e);
                                    $.ajax({
                                        url: 'MessageServlet',
                                        dataType: "json",
                                        contentType: "application/json; charset=utf-8",
                                        data: {'getAllMessages': 'getAllMessages'},
                                        cache: true,
                                        success: function(data) {
                                            console.log(data);
                                            e.popover({html: true,
                                                content: function() {
                                                    var notifications = '<div class="col-md-10 col-xs-10 col-lg-10" ><ul class="list - group">';
                                                    $.each(data, function(key, val) {
                                                        notifications += '<li class="list-group-item"><div class="col-xs-5 col-md-5 col-lg-4">From:' + val.from + '</div><div class="row">' + val.message + "</div> </li>"
                                                    });
                                                    notifications += '</ul></div>';
                                                    return notifications;
                                                }

                                            }).popover('show');
//                                                                    return data;
                                            $("#message").html('0');
                                            $.ajax({
                                                url: 'MessageServlet',
                                                dataType: "json",
                                                contentType: "application/json; charset=utf-8",
                                                data: {'markseen': 'count'},
                                                cache: true,
                                                success: function(data) {
                                                    console.log(data);
                                                },
                                                error: function(data) {
                                                    console.log("error" + JSON.stringify(data))
                                                }
                                            });
                                        },
                                        error: function(data) {
                                            console.log("error" + JSON.stringify(data))
                                        }
                                    });
                                });

                                $('[data-toggle="popover"]').click(function() {
                                    var e = $(this);
                                    console.log(e);
                                    $.ajax({
                                        url: 'NotificationServlet',
//                    dataType: "json",
                                        contentType: "application/json; charset=utf-8",
                                        data: {'getNotification': 'count'},
                                        cache: true,
                                        success: function(data) {
                                            console.log(data);
                                            e.popover({html: true,
                                                content: function() {
                                                    if ($('#notification').html() == '0') {
                                                        return '<div class="col-md-10 col-xs-10 col-lg-10" >No new Notifications</div>';
                                                    }
                                                    var notifications = '<div class="col-md-10 col-xs-10 col-lg-10"  ><ul class="list - group" ">';
                                                    $('#notification').css('width','200px');
                                                    $('#notification').css('height','200px');
                                                    $.each(data, function(key, val) {
                                                        notifications += '<li class="list-group-item" style="width:200px;height:200px;">' + val.subject + " </li>"
                                                    });
                                                    notifications += '</ul></div>';
                                                    return notifications;
                                                }

                                            }).popover('show');
//                                                                    return data;
                                            $("#notification").html('0');
                                            $.ajax({
                                                url: 'NotificationServlet',
                                                dataType: "json",
                                                contentType: "application/json; charset=utf-8",
                                                data: {'markseen': 'count'},
                                                cache: true,
                                                success: function(data) {
                                                    console.log(data);
                                                    // $('#output ul').append('<li>The feed loads fine');

                                                    $('#notification').html(data.notificationCount);
                                                    $('#message').html(data.messageCount);
//
                                                },
                                                error: function(data) {
                                                    console.log("error" + JSON.stringify(data))
                                                }
                                            });
                                        },
                                        error: function(data) {
                                            console.log("error" + JSON.stringify(data))
                                        }
                                    });

                                });
//                                $('[data-toggle="tooltip"]').popover();
                                var options = {
                                    $AutoPlay: true, //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
                                    $AutoPlaySteps: 1, //[Optional] Steps to go for each navigation request (this options applys only when slideshow disabled), the default value is 1
                                    $AutoPlayInterval: 2000, //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
                                    $PauseOnHover: 1, //[Optional] Whether to pause when mouse over if a slider is auto playing, 0 no pause, 1 pause for desktop, 2 pause for touch device, 3 pause for desktop and touch device, 4 freeze for desktop, 8 freeze for touch device, 12 freeze for desktop and touch device, default value is 1

                                    $ArrowKeyNavigation: true, //[Optional] Allows keyboard (arrow key) navigation or not, default value is false
                                    $SlideEasing: $JssorEasing$.$EaseOutQuint, //[Optional] Specifies easing for right to left animation, default value is $JssorEasing$.$EaseOutQuad
                                    $SlideDuration: 800, //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
                                    $MinDragOffsetToSlide: 20, //[Optional] Minimum drag offset to trigger slide , default value is 20
                                    //$SlideWidth: 600,                                 //[Optional] Width of every slide in pixels, default value is width of 'slides' container
                                    //$SlideHeight: 300,                                //[Optional] Height of every slide in pixels, default value is height of 'slides' container
                                    $SlideSpacing: 0, //[Optional] Space between each slide in pixels, default value is 0
                                    $DisplayPieces: 1, //[Optional] Number of pieces to display (the slideshow would be disabled if the value is set to greater than 1), the default value is 1
                                    $ParkingPosition: 0, //[Optional] The offset position to park slide (this options applys only when slideshow disabled), default value is 0.
                                    $UISearchMode: 1, //[Optional] The way (0 parellel, 1 recursive, default value is 1) to search UI components (slides container, loading screen, navigator container, arrow navigator container, thumbnail navigator container etc).
                                    $PlayOrientation: 1, //[Optional] Orientation to play slide (for auto play, navigation), 1 horizental, 2 vertical, 5 horizental reverse, 6 vertical reverse, default value is 1
                                    $DragOrientation: 1, //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)

                                    $ArrowNavigatorOptions: {//[Optional] Options to specify and enable arrow navigator or not
                                        $Class: $JssorArrowNavigator$, //[Requried] Class to create arrow navigator instance
                                        $ChanceToShow: 2, //[Required] 0 Never, 1 Mouse Over, 2 Always
                                        $AutoCenter: 2, //[Optional] Auto center arrows in parent container, 0 No, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
                                        $Steps: 1, //[Optional] Steps to go for each navigation request, default value is 1
                                        $Scale: false
                                                //Scales bullets navigator or not while slider scale
                                    },
                                    $BulletNavigatorOptions: {//[Optional] Options to specify and enable navigator or not
                                        $Class: $JssorBulletNavigator$, //[Required] Class to create navigator instance
                                        $ChanceToShow: 2, //[Required] 0 Never, 1 Mouse Over, 2 Always
                                        $AutoCenter: 1, //[Optional] Auto center navigator in parent container, 0 None, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
                                        $Steps: 1, //[Optional] Steps to go for each navigation request, default value is 1
                                        $Lanes: 1, //[Optional] Specify lanes to arrange items, default value is 1
                                        $SpacingX: 12, //[Optional] Horizontal space between each item in pixel, default value is 0
                                        $SpacingY: 4, //[Optional] Vertical space between each item in pixel, default value is 0
                                        $Orientation: 1, //[Optional] The orientation of the navigator, 1 horizontal, 2 vertical, default value is 1
                                        $Scale: false
                                                //Scales bullets navigator or not while slider scale
                                    }
                                };
                                //Make the element 'slider1_container' visible before initialize jssor slider.
                                $("#slider1_container").css("display", "block");
                                var jssor_slider1 = new $JssorSlider$(
                                        "slider1_container", options);
                                //responsive code begin
                                //you can remove responsive code if you don't want the slider scales while window resizes
                                function ScaleSlider() {
                                    var parentWidth = jssor_slider1.$Elmt.parentNode.clientWidth;
                                    if (parentWidth) {
                                        jssor_slider1.$ScaleWidth(parentWidth - 30);
                                    } else
                                        window.setTimeout(ScaleSlider, 30);
                                }
                                ScaleSlider();
                                $(window).bind("load", ScaleSlider);
                                $(window).bind("resize", ScaleSlider);
                                $(window).bind("orientationchange", ScaleSlider);
                            });
            
        </script>
    </body>
</html>
