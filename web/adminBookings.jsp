<%-- 
    Document   : adminBookings
    Created on : 7 Oct, 2025, 10:56:51 AM
    Author     : User
--%>

<%@ page import="java.util.*, com.java.BookingsDetails, com.java.Booking" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Manage Bookings</title>
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
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: left; }
        th { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; margin: 2px; }
        .accept { background-color: #4CAF50; color: white; }
        .reject { background-color: #f44336; color: white; }
    </style>
</head>
<body>
    <h1>All Bookings</h1>
    <%
        BookingsDetails bd = new BookingsDetails();
        List<Booking> bookings = bd.getAllBookings();
    %>
    <table>
        <tr>
            <th>ID</th><th>Name</th><th>Email</th><th>Phone</th><th>Date/Time</th><th>Guests</th><th>Requests</th><th>Status</th><th>Actions</th>
        </tr>
        <%
            for (Booking b : bookings) {
        %>
        <tr>
            <td><%= b.getId() %></td>
            <td><%= b.getName() %></td>
            <td><%= b.getEmail() %></td>
            <td><%= b.getPhone() %></td>
            <td><%= b.getBookingDateTime() %></td>
            <td><%= b.getGuests() %></td>
            <td><%= b.getSpecialRequests() %></td>
            <td><%= b.getStatus() %></td>
            <td>
                <form action="updateBooking.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= b.getId() %>">
                    <input type="hidden" name="status" value="Confirmed">
                    <button type="submit" class="btn accept">Accept</button>
                </form>
                <form action="updateBooking.jsp" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= b.getId() %>">
                    <input type="hidden" name="status" value="Cancelled">
                    <button type="submit" class="btn reject">Reject</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
