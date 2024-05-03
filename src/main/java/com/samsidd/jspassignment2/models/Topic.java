package com.samsidd.jspassignment2.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Topic {
    int topic_id;
    String topic_name;

    public Topic(int topic_id, String topic_name) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
    }

    public Topic(String topic_name) {
        this(0, topic_name);
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public static Topic FromResultSetObject(ResultSet rs) throws SQLException {
        return new Topic(
                rs.getInt("topic_id"),
                rs.getString("topic_name")
        );
    }
}
