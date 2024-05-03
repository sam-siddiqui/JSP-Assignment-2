package com.samsidd.jspassignment2.dao;

import com.samsidd.jspassignment2.controllers.DBConnection;
import com.samsidd.jspassignment2.models.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationDAO {
    long lastFetchedAtUnixTime;
    long lastUpdateAtUnixTime;
    DBConnection conn;

    public ReservationDAO() {
        this.conn = DBConnection.getInstance();
    }

    public ArrayList<Reservation> getReservedBooksByUserID(int user_id) {
        String raw_sql_query =
                "SELECT * FROM reservations " +
                "WHERE user_id = ?";
        ArrayList<Reservation> bookIDsReservedByUser = new ArrayList<>();
        conn.setPreparedStatement(raw_sql_query, String.valueOf(user_id));

        try (ResultSet rs = conn.runFetchPreparedStatement();) {
            if(rs != null) {
                while (rs.next()) {
                    Reservation r = Reservation.FromResultSetObject(rs);
                    bookIDsReservedByUser.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookIDsReservedByUser;
    }

    public boolean reserveBookForUser(int book_id, int user_id) {
        boolean isReserved = false;
        String raw_sql_query =
                "INSERT INTO reservations " +
                "(user_id, book_id) " +
                "VALUES" +
                " (?, ?)";
        conn.setPreparedStatement(
                raw_sql_query,
                String.valueOf(user_id),
                String.valueOf(book_id)
        );

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected == 1) isReserved = true;

        return isReserved;
    }

    public boolean returnAllReservedBooksFromUser(int user_id) {
        boolean isReturned = false;
        String raw_sql_query =
                "DELETE FROM reservations " +
                "WHERE user_id = ?";
        conn.setPreparedStatement(
                raw_sql_query,
                String.valueOf(user_id)
        );

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected > 0) isReturned = true;

        return isReturned;
    }

    public boolean returnReservedBookFromUser(int book_id, int user_id) {
        boolean isReturned = false;
        String raw_sql_query =
                "DELETE FROM reservations " +
                "WHERE user_id = ? " +
                "AND book_id = ?";
        conn.setPreparedStatement(
                raw_sql_query,
                String.valueOf(user_id),
                String.valueOf(book_id)
        );

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected == 1) isReturned = true;

        return isReturned;
    }
}
