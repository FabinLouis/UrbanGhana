<%-- 
    Document   : booktable.jsp
    Created on : 7 Oct, 2025, 10:14:44 AM
    Author     : User
--%>

<%@page import="com.java.User"%>
<%@page import="com.java.RegAndLogin"%>
<%@page import="java.util.List"%>
<%@page import="com.java.BookingsDetails"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book a table</title>
                       <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nothing+You+Could+Do" rel="stylesheet">

    <link rel="stylesheet" href="css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="css/animate.css">
    
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/magnific-popup.css">

    <link rel="stylesheet" href="css/aos.css">

    <link rel="stylesheet" href="css/ionicons.min.css">

    <link rel="stylesheet" href="css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="css/jquery.timepicker.css">

    
    <link rel="stylesheet" href="css/flaticon.css">
    <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/style.css">
     <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0 auto;
            padding: 20px;
        }
        .booking-form {
            max-width: 500px;
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input, select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
		      <a class="navbar-brand" href="index.html"><span class="flaticon-pizza-1 mr-1"></span>Pizza<br><small>Delicous</small></a>
		      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
		        <span class="oi oi-menu"></span> Menu
		      </button>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item active"><a href="index.html" class="nav-link">Home</a></li>
	          <li class="nav-item"><a href="menu.html" class="nav-link">Menu</a></li>
	          <li class="nav-item"><a href="services.html" class="nav-link">Services</a></li>
	          <li class="nav-item"><a href="booktable.jsp" class="nav-link">Book a table</a></li>
	          <li class="nav-item"><a href="about.html" class="nav-link">About</a></li>
	          <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
                  <%
                  String user = (String)session.getAttribute("userId");
                  %>
                  <script>
                      var name = "<%=user %>";
                  </script>
                  <li class="nav-item"><a href="Login.jsp" class="nav-link" title="Logout"><script>document.write( name );</script></a></li>
	          
	        </ul>
	      </div>
		  </div>
	  </nav>
                 
          <h1>Book a Table</h1>
          <%
             RegAndLogin randl = new RegAndLogin();
              User users = randl.getAllUsers(user);
              
          if (request.getMethod().equalsIgnoreCase("post")) {
          String name = request.getParameter("name");
String email = request.getParameter("email");
String phone = request.getParameter("phone");
String date = request.getParameter("date");
String time = request.getParameter("time");
int guests = Integer.parseInt(request.getParameter("guests"));
String specialRequests = request.getParameter("special-requests");
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String bookingDateTime = date + " " + time + ":00";
BookingsDetails bd = new BookingsDetails();
bd.customerBookingRequest(name, email, phone, bookingDateTime, guests, specialRequests);
response.sendRedirect("bookingconfirmation.jsp");

          }
          %>
    
    <div class="booking-form">
        <form action="" method="post">
            <div class="form-group">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" value="<%= users != null ? users.getFullName() : "" %>" readonly>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                 <input type="email" id="email" name="email" value="<%= users != null ? users.getEmail() : "" %>" readonly>
            </div>
            
            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="tel" id="phone" name="phone" required>
            </div>
            
            <div class="form-group">
                <label for="date">Booking Date:</label>
                <input type="date" id="date" name="date" required>
            </div>
            
            <div class="form-group">
                <label for="time">Time:</label>
                <input type="time" id="time" name="time" required>
            </div>
            
            <div class="form-group">
                <label for="guests">Number of Guests:</label>
                <select id="guests" name="guests" required>
                    <option value="">Select...</option>
                    <option value="1">1 person</option>
                    <option value="2">2 people</option>
                    <option value="3">3 people</option>
                    <option value="4">4 people</option>
                    <option value="5">5 people</option>
                    <option value="6">6 people</option>
                    <option value="7">7+ people</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="special-requests">Special Requests:</label>
                <input type="text" id="special-requests" name="special-requests">
            </div>
            
            <button type="submit">Book Table</button>
        </form>
    </div>
    </body>
</html>
