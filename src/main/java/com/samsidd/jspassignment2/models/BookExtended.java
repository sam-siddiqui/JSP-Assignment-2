package com.samsidd.jspassignment2.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookExtended {

    public int book_id;
    public Topic topic;
    public String book_name;
    public Author author;
    public boolean is_available;

    public BookExtended(){};

    public BookExtended(int book_id, Topic topic, String book_name, Author author, boolean is_available) {
        this.book_id = book_id;
        this.topic = topic;
        this.book_name = book_name;
        this.author = author;
        this.is_available = is_available;
    }

    public static BookExtended FromResultSetObject(ResultSet rs) throws SQLException {
        Book book = new Book (
                rs.getInt("book_id"),
                rs.getInt("topic_id"),
                rs.getString("book_name"),
                rs.getInt("author_id"),
                rs.getBoolean("is_available")
        );
        Topic topic = new Topic(
                rs.getInt("topic_id"),
                rs.getString("topic_name")
        );
        Author author = new Author(
                rs.getInt("author_id"),
                rs.getString("author_name")
        );

        return new BookExtended(
                book.getBook_id(),
                topic,
                book.getBook_name(),
                author,
                book.getIs_available()
        );
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean getIs_available() {
        return is_available;
    }

    public void setIs_available(boolean is_available) {
        this.is_available = is_available;
    }

    @Override
    public String toString() {
        String output = "";
        output += "BookID: " + book_id + ',';
        output += "Topic: " + topic + ',';
        output += "Book Name: " + book_name + ',';
        output += "Author: " + author + ',';
        output += "Is Available: " + is_available;

        return output;
    }

}
