package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

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

    public FeedItem() {
        // Default constructor required for calls to DataSnapshot.getValue(FeedItem.class)
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
