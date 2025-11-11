<%-- 
    Document   : updateBooking
    Created on : 7 Oct, 2025, 10:58:43 AM
    Author     : User
--%>

<%@ page import="com.java.BookingsDetails" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    String status = request.getParameter("status");

    BookingsDetails bd = new BookingsDetails();
    bd.updateBookingStatus(id, status);

    response.sendRedirect("adminBookings.jsp");
%>
