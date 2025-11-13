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
public class User {
    
    private String fullName;
    private String UserName;
    private String Email;
    private String Pass;
  public String getFullName() {
        return fullName;
    }
  public void user(String name, String userId, String email, String pass){
  this.fullName = name;
  this.UserName = userId;
  this.Email = email;
  this.Pass = pass;
  }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        this.Pass = pass;
    }
}   

