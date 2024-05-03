package com.samsidd.jspassignment2.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationExtended {
    User user;
    Book book;

    public ReservationExtended() {};

    public ReservationExtended(User user_id, Book book_id){
        this.user = user_id;
        this.book = book_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public static Reservation FromResultSetObject(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation (
                rs.getInt("user_id"),
                rs.getInt("book_id")
        );
        return reservation;
    }

    @Override
    public String toString() {
        String output = "";
        output += "UserID: " + user + ',';
        output += "BookID: " + book + ',';

        return output;
    }
}
