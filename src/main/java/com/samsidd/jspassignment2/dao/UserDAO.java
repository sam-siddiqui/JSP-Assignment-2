package com.samsidd.jspassignment2.dao;

import com.samsidd.jspassignment2.controllers.DBConnection;
import com.samsidd.jspassignment2.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public int totalBooksReserved;
    long lastFetchedAtUnixTime;
    long lastUpdateAtUnixTime;
    int lastCreatedUserKey;
    DBConnection conn;

    public UserDAO() {
        this.conn = DBConnection.getInstance();
    }

    public User getUserByID(int user_id) {
        String raw_sql_query =
                "SELECT * FROM users " +
                "WHERE user_id = ?";
        User user = null;
        conn.setPreparedStatement(raw_sql_query, String.valueOf(user_id));

        try (ResultSet rs = conn.runFetchPreparedStatement()) {
            if(rs != null && rs.next()) user = User.FromResultSetObject(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User getUserByUserName(String username) {
        String raw_sql_query =
                "SELECT * FROM users " +
                "WHERE username = ?";
        User user = null;
        conn.setPreparedStatement(raw_sql_query, username);

        try (ResultSet rs = conn.runFetchPreparedStatement()) {
            if(rs != null && rs.next()) user = User.FromResultSetObject(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean createUser(User user) {
        String raw_sql_query =
                "INSERT INTO users " +
                "(user_id, fname, lname, username, password) " +
                "VALUES" +
                " (?, ?, ?, ?, ?)";
        conn.setPreparedStatement(
                raw_sql_query,
                String.valueOf(user.getUser_id()),
                user.getFname(),
                user.getLname(),
                user.getUsername(),
                user.getPassword()
        );
        boolean status = false;

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected == 1) status = true;
        if(numRowsAffected == 1) this.lastCreatedUserKey = this.getUserByUserName(user.getUsername()).getUser_id();

        return status;
    }

    public boolean updateUser(User user) {
        String raw_sql_query =
                "UPDATE users " +
                "SET fname = ?, " +
                "lname = ?, " +
                "username = ?, " +
                "password = ? " +
                "WHERE user_id = " + user.getUser_id() + ";";
        conn.setPreparedStatement(
                raw_sql_query,
                user.getFname(),
                user.getLname(),
                user.getUsername(),
                user.getPassword()
        );
        boolean status = false;

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected == 1) status = true;

        return status;
    }

    public int getLastCreatedUserKey() {
        return this.lastCreatedUserKey;
    }
}
