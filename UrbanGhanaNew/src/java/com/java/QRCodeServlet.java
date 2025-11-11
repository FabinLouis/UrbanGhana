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
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet("/generateQR")
public class QRCodeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int bookingId = Integer.parseInt(request.getParameter("id"));
        Booking booking = new Booking();
        try {
            booking = new BookingsDetails().getBookingById(bookingId); // Use your DAO method
        } catch (SQLException ex) {
            Logger.getLogger(QRCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String qrData = String.format(
            "Booking ID: %s\nName: %s\nEmail: %s\nPhone: %s\nDateTime: %s\nGuests: %d\nRequests: %s\nStatus: %s",
            booking.getId(), booking.getName(), booking.getEmail(), booking.getPhone(),
            booking.getBookingDateTime(), booking.getGuests(), booking.getSpecialRequests(), booking.getStatus()
        );

        int size = 250;
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, size, size);
        } catch (WriterException ex) {
            Logger.getLogger(QRCodeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage image = MatrixToImageWriter.toBufferedImage(matrix);

        response.setContentType("image/png");
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "png", out);
        out.close();
    }
}
