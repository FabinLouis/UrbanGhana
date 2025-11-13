/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author User
 */
public class FeedbackSql {
    public void insertFeedback(String fname,String lname,String feedback){
        try (Connection con = DBcon.getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO appointment_messages (first_name, last_name, message) VALUES (?, ?, ?)")  ){
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, feedback);
           int res = ps.executeUpdate();
           if(res>0){
               
            System.out.println("Insertion Sucessfull");
           }else{
            System.out.println("Insertion Failed....");
           }
            
        } catch (Exception e) {
            System.out.println("");
        }
    }
    
}
