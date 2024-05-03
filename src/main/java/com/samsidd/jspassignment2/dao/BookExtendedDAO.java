package com.samsidd.jspassignment2.dao;

import com.samsidd.jspassignment2.controllers.DBConnection;
import com.samsidd.jspassignment2.models.Book;
import com.samsidd.jspassignment2.models.BookExtended;
import com.samsidd.jspassignment2.models.Topic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookExtendedDAO {
    int totalBooksCount;
    ArrayList<BookExtended> totalBooks;
    ArrayList<BookExtended> totalAvailableBooks;
    long lastFetchedAtUnixTime;
    DBConnection conn;

    public BookExtendedDAO() {
        this.conn = DBConnection.getInstance();
    }

    public ArrayList<BookExtended> getAllBooks() {
        String getAllAvailableBookQuery =
                "SELECT * FROM books b " +
                        "INNER JOIN authors a ON a.author_id = b.author_id " +
                        "INNER JOIN topics t ON t.topic_id = b.topic_id;";
        ArrayList<BookExtended> totalBooks = new ArrayList<>();

        conn.setQuery(getAllAvailableBookQuery);

        try (ResultSet rs = conn.runFetchQuery();) {
            if(rs != null) {
                while (rs.next()) {
                    BookExtended b = BookExtended.FromResultSetObject(rs);
                    totalBooks.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.totalBooks = totalBooks;
        this.totalBooksCount = totalBooks.size();
        this.lastFetchedAtUnixTime = System.currentTimeMillis() / 1000L;

        return totalBooks;
    }

    public ArrayList<BookExtended> getAllAvailableBooks() {
        String getAllAvailableBookQuery =
                "SELECT * FROM books b " +
                "INNER JOIN authors a ON a.author_id = b.author_id " +
                "INNER JOIN topics t ON t.topic_id = b.topic_id " +
                "WHERE b.is_available = 1";
        ArrayList<BookExtended> availableBooks = new ArrayList<>();

        conn.setQuery(getAllAvailableBookQuery);

        try (ResultSet rs = conn.runFetchQuery();) {
            if(rs != null) {
                while (rs.next()) {
                    BookExtended b = BookExtended.FromResultSetObject(rs);
                    availableBooks.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.totalAvailableBooks = availableBooks;
        this.lastFetchedAtUnixTime = System.currentTimeMillis() / 1000L;

        return availableBooks;
    }

    public ArrayList<BookExtended> getAllAvailableBooksByTopicID(int topic_id) {
        String getAllAvailableBookQuery =
                "SELECT * FROM books b " +
                "INNER JOIN authors a ON a.author_id = b.author_id " +
                "INNER JOIN topics t ON t.topic_id = b.topic_id " +
                "WHERE b.topic_id = ? " +
                "AND is_available = 1";
        ArrayList<BookExtended> availableBooks = new ArrayList<>();

        conn.setPreparedStatement(getAllAvailableBookQuery, String.valueOf(topic_id));

        try (ResultSet rs = conn.runFetchPreparedStatement();) {
            if(rs != null) {
                while (rs.next()) {
                    BookExtended b = BookExtended.FromResultSetObject(rs);
                    availableBooks.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.lastFetchedAtUnixTime = System.currentTimeMillis() / 1000L;

        return availableBooks;
    }

    public ArrayList<BookExtended> getBooksByID(ArrayList<Integer> bookIDs) {
        if(bookIDs.isEmpty()) return new ArrayList<BookExtended>();

        StringBuilder raw_sql_query =
                new StringBuilder(
                        "Select * FROM books b " +
                        "INNER JOIN authors a ON a.author_id = b.author_id " +
                        "INNER JOIN topics t ON t.topic_id = b.topic_id " +
                        "WHERE b.book_id IN ("
                );
        for (int bookID : bookIDs) {
            raw_sql_query.append(bookID).append(", ");
        }
        raw_sql_query.deleteCharAt(raw_sql_query.length()-1);
        raw_sql_query.deleteCharAt(raw_sql_query.length()-1);
        raw_sql_query.append(");");
        ArrayList<BookExtended> reservedBooks = new ArrayList<>();

        conn.setPreparedStatement(raw_sql_query.toString());

        try (ResultSet rs = conn.runFetchPreparedStatement();) {
            if(rs != null) {
                while (rs.next()) {
                    BookExtended b = BookExtended.FromResultSetObject(rs);
                    reservedBooks.add(b);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservedBooks;
    }

    public ArrayList<Topic> getAllBookTopics() {
        ArrayList<Topic> allTopics = new ArrayList<>();
        String raw_sql_query = "SELECT * FROM topics";
        conn.setQuery(raw_sql_query);

        try (ResultSet rs = conn.runFetchQuery();) {
            if(rs != null) {
                while (rs.next()) {
                    Topic t = Topic.FromResultSetObject(rs);
                    allTopics.add(t);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allTopics;
    }
}
