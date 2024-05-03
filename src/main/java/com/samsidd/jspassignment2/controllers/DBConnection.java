package com.samsidd.jspassignment2.controllers;

import java.sql.*;

public class DBConnection {
    private static Connection conn = null;
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String dbName = "library_catalog";
    private final String userName = "jsp_assignment";
    private final String password = "password";
    boolean status = false;
    boolean isInsertQuery = false;
    int lastInsertKey = 0;
    CallableStatement rawQuery = null;
    PreparedStatement preparedStatement = null;
    String lastMessageFromDB = null;
    int statusCode = -1;

    private static DBConnection instance = null;

    public DBConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            conn = DriverManager.getConnection(url + dbName, userName, password);
            if(conn == null) throw new Exception("Failed to Connect");

            status = true;
            statusCode = 1;
            lastMessageFromDB = "Connected to DB";
        } catch (SQLException sqlException) {
            status = false;
            conn = null;
            lastMessageFromDB = sqlException.getMessage();
            statusCode = sqlException.getErrorCode();
        } catch (Exception e) {
            status = false;
            conn = null;
            lastMessageFromDB = e.getMessage();
            statusCode = -1;
        }
    }

    public static synchronized DBConnection getInstance() {
        if(instance == null) instance = new DBConnection();

        return instance;
    }

    public void setQuery(String query) {
        try {
            rawQuery = conn.prepareCall(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setIsInsertQuery() {
        this.isInsertQuery = true;
    }

    public void setPreparedStatement(String query, String... arguments) {
        try {
            preparedStatement = conn.prepareStatement(query);

            for (int i = 0; i < arguments.length; i++) {
                preparedStatement.setString(i + 1, arguments[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet runFetchQuery() {
        if(rawQuery == null) throw new RuntimeException("No Query Set");

        ResultSet rs = null;
        try {
            rs = rawQuery.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public int runUpdateQuery() {
        if(rawQuery == null) throw new RuntimeException("No Query Set");

        int numRowsAffected = 0;
        try {
            numRowsAffected = rawQuery.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numRowsAffected;
    }

    public ResultSet runFetchPreparedStatement() {
        if(preparedStatement == null) throw new RuntimeException("No PreparedStatement set");

        ResultSet rs = null;
        try {
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public int runUpdatePreparedStatement() {

        if(preparedStatement == null) throw new RuntimeException("No PreparedStatement set");

        int numRowsAffected = 0;
        try {
            numRowsAffected = preparedStatement.executeUpdate();
            if(isInsertQuery) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                rs.next();
                lastInsertKey = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return numRowsAffected;
    }

    public int getGeneratedInsertKey() {
        return lastInsertKey;
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
