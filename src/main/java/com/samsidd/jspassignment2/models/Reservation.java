package com.samsidd.jspassignment2.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Reservation {

    int user_id;
    int book_id;

    public Reservation() {};

    public Reservation(int user_id, int book_id){
        this.user_id = user_id;
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public static Reservation FromResultSetObject(ResultSet rs) throws SQLException {
        return new Reservation (
                rs.getInt("user_id"),
                rs.getInt("book_id")
        );
    }

    @Override
    public String toString() {
        String output = "";
        output += "UserID: " + user_id + ',';
        output += "BookID: " + book_id + ',';

        return output;
    }
}
