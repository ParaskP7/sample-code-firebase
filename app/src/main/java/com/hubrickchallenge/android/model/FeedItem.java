package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import org.joda.time.DateTime;

@IgnoreExtraProperties
public class FeedItem {

    @PropertyName("type")
    private String type;
    @PropertyName("id")
    private String id;
    @PropertyName("payload")
    private Payload payload;
    @PropertyName("author")
    private Author author;

    private DateTime updatedAt;

    public FeedItem() { // Default constructor required for calls to DataSnapshot.getValue(FeedItem.class)
        updatedAt = new DateTime();
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public Payload getPayload() {
        return payload;
    }

    public Author getAuthor() {
        return author;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", payload=" + payload +
                ", author=" + author +
                '}';
    }

}
