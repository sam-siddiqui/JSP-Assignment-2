package com.samsidd.jspassignment2.models;
import com.samsidd.jspassignment2.Utils;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    int user_id;
    String fname;
    String lname;
    String username;
    String password;

    public User(){};

    public User(int user_id, String fname, String lname, String username, String password) {
        this.user_id = user_id;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
    }

    private String hashPassword(String password) {
        String hashedPassword = null;
        try {
            hashedPassword = Utils.hashString(password);
        } catch (NoSuchAlgorithmException e) {
            hashedPassword = password;
        }
        return hashedPassword;
    }

    public User(String fname, String lname, String username, String password) {
        this(0, fname, lname, username, password);
//        this.password = hashPassword(password);
    }

    public static User FromResultSetObject(ResultSet rs) throws SQLException {
        return new User (
                rs.getInt("user_id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("username"),
                rs.getString("password")
        );
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String output = "";
        output += "UserID: " + user_id + ',';
        output += "First Name: " + fname + ',';
        output += "Book Name: " + lname + ',';
        output += "User Name: " + username + ',';
        output += "Password: " + password;

        return output;
    }
}
