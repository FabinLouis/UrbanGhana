/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet{
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form values
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String message = request.getParameter("message");
        FeedbackSql fs = new FeedbackSql();
        fs.insertFeedback(firstName, lastName, message);

      // Set response type
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8"); // Important for special chars/fonts
    PrintWriter out = response.getWriter();

    // Full HTML with styling/fonts
    out.println("<!DOCTYPE html>");
    out.println("<html lang='en'>");
    out.println("<head>");
    out.println("    <meta charset='UTF-8'>");
    out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
    out.println("    <title>Thank You</title>");

    // Google Fonts
    out.println("    <link href='https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700' rel='stylesheet'>");
    out.println("    <link href='https://fonts.googleapis.com/css?family=Josefin+Sans' rel='stylesheet'>");
    out.println("    <link href='https://fonts.googleapis.com/css?family=Nothing+You+Could+Do' rel='stylesheet'>");

    // Your CSS files (make sure paths are correct relative to context root)
    out.println("    <link rel='stylesheet' href='css/open-iconic-bootstrap.min.css'>");
    out.println("    <link rel='stylesheet' href='css/animate.css'>");
    out.println("    <link rel='stylesheet' href='css/owl.carousel.min.css'>");
    out.println("    <link rel='stylesheet' href='css/owl.theme.default.min.css'>");
    out.println("    <link rel='stylesheet' href='css/magnific-popup.css'>");
    out.println("    <link rel='stylesheet' href='css/aos.css'>");
    out.println("    <link rel='stylesheet' href='css/ionicons.min.css'>");
    out.println("    <link rel='stylesheet' href='css/bootstrap-datepicker.css'>");
    out.println("    <link rel='stylesheet' href='css/jquery.timepicker.css'>");
    out.println("    <link rel='stylesheet' href='css/flaticon.css'>");
    out.println("    <link rel='stylesheet' href='css/icomoon.css'>");
    out.println("    <link rel='stylesheet' href='css/style.css'>");

    out.println("</head>");
    out.println("<body>");

    // Styled thank you message
    out.println("    <div style='font-family: \"Poppins\", sans-serif; text-align: center; padding: 50px;'>");
    out.println("        <h2 style='color: #2c3e50;'>Thank you for your submission!</h2>");
    out.println("        <p><strong>First Name:</strong> " + (firstName) + "</p>");
    out.println("        <p><strong>Last Name:</strong> " + (lastName) + "</p>");
    out.println("        <a href='index.html' style='display: inline-block; margin-top: 20px; padding: 10px 20px; background: #3498db; color: white; text-decoration: none; border-radius: 5px;'>Back to Home</a>");
    out.println("    </div>");

    out.println("</body>");
    out.println("</html>");
}
}
    

