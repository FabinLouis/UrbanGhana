/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet("/generateQRPopup")
public class QRCodePopupServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing booking ID");
            return;
        }

        int bookingId = Integer.parseInt(idParam);
        Booking booking;
        try {
            booking = new BookingsDetails().getBookingById(bookingId);
            if (booking == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Booking not found");
                return;
            }
        } catch (Exception ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving booking");
            return;
        }

        String qrData = String.format(
            "Booking ID: %s\nName: %s\nEmail: %s\nPhone: %s\nDateTime: %s\nGuests: %d\nRequests: %s\nStatus: %s",
            booking.getId(), booking.getName(), booking.getEmail(), booking.getPhone(),
            booking.getBookingDateTime(), booking.getGuests(), booking.getSpecialRequests(), booking.getStatus()
        );

        int size = 250;
        BufferedImage image;
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, size, size);
            image = MatrixToImageWriter.toBufferedImage(matrix);
        } catch (WriterException ex) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "QR generation failed");
            return;
        }

        // Convert image to Base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        String base64Image = Base64.getEncoder().encodeToString(baos.toByteArray());

        // Return HTML popup with embedded QR code
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>QR Code</title></head>");
        out.println("<body style='text-align:center; font-family:sans-serif; padding-top:20px;'>");
        out.println("<h3>Booking QR Code</h3>");
        out.println("<img src='data:image/png;base64," + base64Image + "' style='width:250px; height:250px;'/>");
        out.println("<br/><br/><button onclick='window.close()'>Close</button>");
        out.println("</body></html>");
        out.close();
    }
}