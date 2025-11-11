<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Booking QR Code</title></head>
<body>
    <h2>Your Booking QR Code</h2>
    <img src="generateQR?id=<%= request.getParameter("id") %>" alt="QR Code"/>
</body>
</html>