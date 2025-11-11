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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class RegAndLogin {
    public void register(User user){
    try (Connection con = DBcon.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, fullname, email, pass)VALUES(?, ?, ?,?)")  ){
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getFullName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPass());
           int res = ps.executeUpdate();
           if(res>0){
               
            System.out.println("Registration Sucessfull");
           }else{
            System.out.println("Registration Failed....");
           }
            
        } catch (Exception e) {
            System.out.println("");
        }
    }
    public void LoginUser(String username,String password){
        try (Connection con = DBcon.getConnection();
              PreparedStatement pstmt = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")   ){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()){
                
            } catch (Exception e) {
                System.out.println("Login failed....");
                        
            }
            
        } catch (Exception e) {
                System.out.println("Connection failed....");
        }
    }
    
public User getAllUsers(String userid) throws SQLException {
    User user = null;
    String query = "SELECT username, fullname, email FROM users WHERE username = ?;";

    try (Connection conn = DBcon.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, userid);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                user = new User();
                user.setUserName(rs.getString("username"));
                user.setFullName(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
            }
        }
    }
    return user;
}
}   

