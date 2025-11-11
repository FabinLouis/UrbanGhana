/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class BookingsDetails {
    
    public void customerBookingRequest(String name, String email, String phone, String bookingDateTime, int guests, String specialRequests){
        try (Connection con = DBcon.getConnection();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO bookings (customer_name, email, phone, booking_datetime, guests, special_requests, status) VALUES (?, ?, ?, ?, ?, ?, 'Pending')")   ){
            // Set parameters
    pstmt.setString(1, name);
    pstmt.setString(2, email);
    pstmt.setString(3, phone);
    pstmt.setString(4, bookingDateTime);
    pstmt.setInt(5, guests);
    pstmt.setString(6, specialRequests);
    pstmt.executeUpdate();
        } catch (Exception e) {
        }
    }
    public List<Booking> getAllBookings() {
          List<Booking> bookings = new ArrayList<>();

    String sql = "SELECT *\n" +
"FROM bookings\n" +
"WHERE status IN ('pending', 'confirmed')\n" +
"ORDER BY booking_datetime DESC;";

    try (Connection conn = DBcon.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Booking b = new Booking();
            b.setId(rs.getString("booking_id"));
            b.setName(rs.getString("customer_name"));
            b.setEmail(rs.getString("email"));
            b.setPhone(rs.getString("phone"));
            b.setBookingDateTime(rs.getString("booking_datetime"));
            b.setGuests(rs.getInt("guests"));
            b.setSpecialRequests(rs.getString("special_Requests"));
            b.setStatus(rs.getString("status"));

            bookings.add(b);
        }

    } catch (SQLException e) {
        e.printStackTrace(); // You can log this instead
    }

    return bookings;
        
    
}

public void updateBookingStatus(int bookingId, String status) {
     String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";

    try (Connection conn = DBcon.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, status);
        stmt.setInt(2, bookingId);

        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace(); // Consider logging this instead
    }}

public List<Booking> getBookingsByUser(String username) throws SQLException {
    List<Booking> bookings = new ArrayList<>();
    String query = "SELECT booking_id, customer_name, email, phone, guests, special_requests, status FROM bookings WHERE customer_name = ? ORDER BY booking_datetime DESC";

    try (Connection conn = DBcon.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, username);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Booking b = new Booking();
                b.setId(rs.getString("booking_id"));
            b.setName(rs.getString("customer_name"));
                b.setEmail(rs.getString("email"));
                b.setPhone(rs.getString("phone"));
                b.setGuests(rs.getInt("guests"));
                b.setSpecialRequests(rs.getString("special_requests"));
                b.setStatus(rs.getString("status"));
                bookings.add(b);
            }
        }
    }
    return bookings;
}
public Booking getBookingById(int id) throws SQLException {
    Booking booking = null;
    String query = "SELECT  booking_id, customer_name, email, phone, booking_datetime, guests, special_requests, status FROM bookings WHERE booking_id = ?";

    try (Connection conn = DBcon.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, id);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                booking = new Booking();
                booking.setId(rs.getString("booking_id"));
                booking.setName(rs.getString("customer_name"));
                booking.setEmail(rs.getString("email"));
                booking.setPhone(rs.getString("phone"));
                Timestamp timestamp = rs.getTimestamp("booking_datetime");
                String formattedDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
                booking.setBookingDateTime(formattedDateTime);
                booking.setGuests(rs.getInt("guests"));
                booking.setSpecialRequests(rs.getString("special_requests"));
                booking.setStatus(rs.getString("status"));
            }
        }
    }
    return booking;
}
    
}
