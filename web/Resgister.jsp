<%-- 
    Document   : Resgister
    Created on : 24 Sep, 2025, 10:07:54 AM
    Author     : User
--%>

<%@page import="com.java.RegAndLogin"%>
<%@page import="com.java.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
.outer {
  height: 100vh;              /* full viewport height */
  display: flex;              /* enable flexbox */
  justify-content: center;    /* center horizontally */
  align-items: center;        /* center vertically */
  text-align: center;
 
}
.inner {
  padding: 20px;
  background-image: url("images/pexels-karolina-grabowska-4046718.jpg");
  background-repeat: no-repeat;   /* don’t tile it */
  background-size: cover;        /* scale to fill screen */
  background-position: center;
  border-radius: 15px;
  box-shadow: 
    0 0 5px #FFD700,     /* bright gold near */
    0 0 15px #FFA500,    /* slightly orange mid glow */
    0 0 25px #FFD700;    /* outer soft glow */
  color: black;
 
}
h2{
    color: black;
}
a{
    color: black;
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
	          <li class="nav-item"><a href="blog.html" class="nav-link">Blog</a></li>
	          <li class="nav-item"><a href="about.html" class="nav-link">About</a></li>
	          <li class="nav-item"><a href="contact.html" class="nav-link">Contact</a></li>
	    
	          
	        </ul>
	      </div>
		  </div>
	  </nav>
        <%
        if (request.getMethod().equalsIgnoreCase("post")) {
            String name = request.getParameter("fullname");
            String userId = request.getParameter("username");
            String email = request.getParameter("email");
            String pass = request.getParameter("password");
             User u = new User();
             u.user(name, userId, email, pass);
             RegAndLogin randl = new RegAndLogin();
             randl.register(u);
             response.sendRedirect("Login.jsp");
        
        }
        %>
        <div class="outer">
  <div class="inner">
        <h2>Register</h2>
<form action="" method="post">
    <label for="fullname">Full Name:</label><br>
    <input type="text" id="fullname" name="fullname" required><br><br>

    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username" required><br><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Register">
</form>
<p>Already have an account? <a href="Login.jsp">Login here</a></p>
  </div></div>
    </body>
</html>
