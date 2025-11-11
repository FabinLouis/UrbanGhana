<%-- 
    Document   : userbooking
    Created on : 9 Oct, 2025, 9:43:09 AM
    Author     : User
--%>

<%@page import="com.java.BookingsDetails"%>
<%@page import="com.java.Booking"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User bookings</title>
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
    .booking-container {
        padding: 20px;
       
    }

    .booking-card {
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        margin-bottom: 20px;
        padding: 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        flex-wrap: wrap;
    }

    .booking-details {
        flex: 1;
        min-width: 250px;
    }

    .booking-details h5 {
        margin: 0 0 10px;
        font-size: 18px;
        color: #333;
    }

    .booking-details p {
        margin: 4px 0;
        color: #555;
        font-size: 14px;
    }

    .booking-actions {
        text-align: right;
        min-width: 180px;
    }

    .booking-actions button,
    .booking-actions a {
        display: block;
        margin-top: 8px;
        background-color: #007bff;
        color: white;
        border: none;
        padding: 8px 12px;
        border-radius: 4px;
        text-decoration: none;
        font-size: 14px;
        text-align: center;
    }

    .booking-actions button:hover,
    .booking-actions a:hover {
        background-color: #0056b3;
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
	          <li class="nav-item"><a href="userbooking.jsp" class="nav-link">My bookings</a></li>
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
        <h1>Your Bookings</h1>
<%
    String name = (String)session.getAttribute("name");
     BookingsDetails bd = new BookingsDetails();
      List<Booking> bookings = bd.getBookingsByUser(name);
%>
<div class="booking-container">
    <%
        for (Booking b : bookings) {
    %>
    <div class="booking-card">
        <div class="booking-details">
            <h5>Booking for - <%= b.getName() %></h5>
            <p>Email: <%= b.getEmail() %></p>
            <p>Phone: <%= b.getPhone() %></p>
            <p>Guests: <%= b.getGuests() %></p>
            <p>Requests: <%= b.getSpecialRequests() %></p>
            <p>Status: <%= b.getStatus() %></p>
        </div>
        <div class="booking-actions">
            <button onclick="showQRPopup(<%= b.getId() %>)">Show QR Code</button>
            <a href="downloadQR?id=<%= b.getId() %>">Download QR as PDF</a>
        </div>
    </div>
    <% } %>
</div>

<script>
    function showQRPopup(id) {
        const contextPath = '<%= request.getContextPath() %>';
        window.open(contextPath + "/generateQRPopup?id=" + id, "QRPopup", "width=320,height=400");
    }
</script>

   
    </body>
</html>
