package com.samsidd.jspassignment2.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {

    public int book_id;
    public int topic_id;
    public String book_name;
    public int author_id;
    public boolean is_available;

    public Book(){};

    public Book(int book_id, int topic_id, String book_name, int author_id, boolean is_available) {
        this.book_id = book_id;
        this.topic_id = topic_id;
        this.book_name = book_name;
        this.author_id = author_id;
        this.is_available = is_available;
    }
    public Book(int topic_id, String book_name, int author_id) {
        this(0, topic_id, book_name, author_id, true);
    }

    public static Book FromResultSetObject(ResultSet rs) throws SQLException {
        return new Book (
                rs.getInt("book_id"),
                rs.getInt("topic_id"),
                rs.getString("book_name"),
                rs.getInt("author_id"),
                rs.getBoolean("is_available")
        );
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    public static Book fromBookExtended(BookExtended bookExtended) {
        return new Book(
                bookExtended.getBook_id(),
                bookExtended.getTopic().getTopic_id(),
                bookExtended.getBook_name(),
                bookExtended.getAuthor().getAuthor_id(),
                bookExtended.getIs_available()
        );
    }

    @Override
    public String toString() {
        String output = "";
        output += "BookID: " + book_id + ',';
        output += "TopicID: " + topic_id + ',';
        output += "Book Name: " + book_name + ',';
        output += "AuthorID: " + author_id + ',';
        output += "Is Available: " + is_available;

        return output;
    }
}
