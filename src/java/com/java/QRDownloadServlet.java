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
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
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
@WebServlet("/downloadQR")
public class QRDownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, MalformedURLException {
        int bookingId = Integer.parseInt(request.getParameter("id"));
        Booking booking=new Booking();
        try {
            booking = new BookingsDetails().getBookingById(bookingId); // Your DAO method
        } catch (SQLException ex) {
            Logger.getLogger(QRDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String qrData = String.format(
            "Booking ID: %s\nName: %s\nEmail: %s\nPhone: %s\nDateTime: %s\nGuests: %d\nRequests: %s\nStatus: %s",
            booking.getId(), booking.getName(), booking.getEmail(), booking.getPhone(),
            booking.getBookingDateTime(), booking.getGuests(), booking.getSpecialRequests(), booking.getStatus()
        );

        // Generate QR code image
        BitMatrix matrix = null;
        try {
            matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.AZTEC.QR_CODE, 200, 200);
        } catch (WriterException ex) {
            Logger.getLogger(QRDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix);

        // Convert BufferedImage to iText Image
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        Image qrPdfImage = null;
        try {
            qrPdfImage = Image.getInstance(baos.toByteArray());
        } catch (BadElementException ex) {
            Logger.getLogger(QRDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set response headers
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=booking_qr.pdf");

        // Create PDF
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
        } catch (DocumentException ex) {
            Logger.getLogger(QRDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.open();
        try {
            document.add(new Paragraph("Booking QR Code"));
        } catch (DocumentException ex) {
            Logger.getLogger(QRDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            document.add(qrPdfImage);
        } catch (DocumentException ex) {
            Logger.getLogger(QRDownloadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.close();
    }
}
