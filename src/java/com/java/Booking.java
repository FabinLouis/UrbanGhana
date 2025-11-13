/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java;

/**
 *
 * @author User
 */

    public class Booking {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String bookingDateTime;
    private int guests;
    private String specialRequests;
    private String status;

    // Getter and Setter for id
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for phone
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter and Setter for bookingDateTime
    public String getBookingDateTime() {
        return bookingDateTime;
    }
    public void setBookingDateTime(String bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    // Getter and Setter for guests
    public int getGuests() {
        return guests;
    }
    public void setGuests(int guests) {
        this.guests = guests;
    }

    // Getter and Setter for specialRequests
    public String getSpecialRequests() {
        return specialRequests;
    }
    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    }
