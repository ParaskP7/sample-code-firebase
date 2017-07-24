package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.hubrickchallenge.android.util.time.TimeUtil;

import org.joda.time.DateTime;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@IgnoreExtraProperties
public class FeedItem extends RealmObject {

    public static final String ID = "id";
    public static final String UPDATED_AT = "updatedAt";

    @PropertyName("type")
    private String type;
    @PropertyName(ID)
    @PrimaryKey
    private String id;
    @PropertyName("payload")
    private Payload payload;
    @PropertyName("author")
    private Author author;

    private String updatedAt;

    public FeedItem() { // Default constructor required for calls to DataSnapshot.getValue(FeedItem.class)
        updatedAt = TimeUtil.dateTimeToString(new DateTime());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                ", payload=" + payload +
                ", author=" + author +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

}
