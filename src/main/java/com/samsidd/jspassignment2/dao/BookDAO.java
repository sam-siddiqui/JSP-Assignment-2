package com.samsidd.jspassignment2.dao;

import com.samsidd.jspassignment2.controllers.DBConnection;
import com.samsidd.jspassignment2.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookDAO {
    int totalBooksCount;
    ArrayList<Book> totalBooks;
    long lastFetchedAtUnixTime;
    long lastUpdateAtUnixTime;
    DBConnection conn;

    public BookDAO() {
        this.conn = DBConnection.getInstance();
    }

    public ArrayList<Book> getAllBooks() {
        String getAllBookQuery = "SELECT * FROM books";
        ArrayList<Book> resultAsArray = new ArrayList<>();

        conn.setQuery(getAllBookQuery);

        try (ResultSet rs = conn.runFetchQuery();) {
            if(rs != null) {
                while (rs.next()) {
                   Book b = Book.FromResultSetObject(rs);
                   resultAsArray.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.totalBooks = resultAsArray;
        this.totalBooksCount = resultAsArray.size();
        this.lastFetchedAtUnixTime = System.currentTimeMillis() / 1000L;
        return resultAsArray;
    }

    public ArrayList<Book> getAllAvailableBooks() {
        String getAllAvailableBookQuery =
                "SELECT * FROM books " +
                "WHERE is_available = 1";
        ArrayList<Book> availableBooks = new ArrayList<>();

        conn.setQuery(getAllAvailableBookQuery);

        try (ResultSet rs = conn.runFetchQuery();) {
            if(rs != null) {
                while (rs.next()) {
                    Book b = Book.FromResultSetObject(rs);
                    availableBooks.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.totalBooks = availableBooks;
        this.totalBooksCount = availableBooks.size();
        this.lastFetchedAtUnixTime = System.currentTimeMillis() / 1000L;

        return availableBooks;
    }

    public Book getBookByID(int book_id) {
        String raw_sql_query =
                "Select * FROM books " +
                "WHERE book_id = ?";
        Book book = null;
        conn.setPreparedStatement(raw_sql_query, String.valueOf(book_id));

        try (ResultSet rs = conn.runFetchPreparedStatement()) {
            if(rs != null && rs.next()) book = Book.FromResultSetObject(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    public boolean makeBookAvailable(Book book) {
        boolean isMadeAvailable = false;
        String raw_sql_query =
                "UPDATE books " +
                "SET is_available = 1 " +
                "WHERE book_id = ?";
        conn.setPreparedStatement(
                raw_sql_query,
                String.valueOf(book.getBook_id())
        );

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected == 1) isMadeAvailable = true;

        if(isMadeAvailable) this.lastUpdateAtUnixTime = System.currentTimeMillis() / 1000L;

        return isMadeAvailable;
    }

    public boolean makeBookAvailable(int book_id) {
        boolean isMadeAvailable = false;
        String raw_sql_query =
                "UPDATE books " +
                "SET is_available = 1 " +
                "WHERE book_id = ?";
        conn.setPreparedStatement(
                raw_sql_query,
                String.valueOf(book_id)
        );

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected == 1) isMadeAvailable = true;

        if(isMadeAvailable) this.lastUpdateAtUnixTime = System.currentTimeMillis() / 1000L;

        return isMadeAvailable;
    }

    public boolean makeBookUnavailable(Book book) {
        boolean isMadeUnavailable = false;
        String raw_sql_query =
                "UPDATE books " +
                "SET is_available = 0 " +
                "WHERE book_id = ?";
        conn.setPreparedStatement(
                raw_sql_query,
                String.valueOf(book.getBook_id())
        );

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected == 1) isMadeUnavailable = true;

        if(isMadeUnavailable) this.lastUpdateAtUnixTime = System.currentTimeMillis() / 1000L;

        return isMadeUnavailable;
    }

    public boolean makeBookUnavailable(int book_id) {
        boolean isMadeUnavailable = false;
        String raw_sql_query =
                "UPDATE books " +
                "SET is_available = 0 " +
                "WHERE book_id = ?";
        conn.setPreparedStatement(
                raw_sql_query,
                String.valueOf(book_id)
        );

        int numRowsAffected = conn.runUpdatePreparedStatement();
        if(numRowsAffected == 1) isMadeUnavailable = true;

        if(isMadeUnavailable) this.lastUpdateAtUnixTime = System.currentTimeMillis() / 1000L;

        return isMadeUnavailable;
    }

    public ArrayList<Book> getBooksByID(ArrayList<Integer> bookIDs) {
        if(bookIDs.isEmpty()) return new ArrayList<Book>();

        StringBuilder raw_sql_query =
                new StringBuilder(
                        "Select * FROM books " +
                        "WHERE book_id IN ("
                );
        for (int bookID : bookIDs) {
            raw_sql_query.append(bookID).append(", ");
        }
        raw_sql_query.deleteCharAt(raw_sql_query.length()-1);
        raw_sql_query.deleteCharAt(raw_sql_query.length()-1);
        raw_sql_query.append(");");
        ArrayList<Book> reservedBooks = new ArrayList<>();

        conn.setPreparedStatement(raw_sql_query.toString());

        try (ResultSet rs = conn.runFetchPreparedStatement();) {
            if(rs != null) {
                while (rs.next()) {
                    Book b = Book.FromResultSetObject(rs);
                    reservedBooks.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservedBooks;
    }

}
