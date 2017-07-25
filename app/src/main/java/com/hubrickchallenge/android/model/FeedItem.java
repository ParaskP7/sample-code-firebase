package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;
import com.hubrickchallenge.android.util.time.TimeUtil;

import org.joda.time.DateTime;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.hubrickchallenge.android.util.GeneralUtil.HASH_CODE;

@IgnoreExtraProperties
public class FeedItem extends RealmObject {

    public static final String ID = "id";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedItem feedItem = (FeedItem) o;

        if (!type.equals(feedItem.type)) return false;
        if (!id.equals(feedItem.id)) return false;
        if (!payload.equals(feedItem.payload)) return false;
        if (!author.equals(feedItem.author)) return false;
        return updatedAt.equals(feedItem.updatedAt);

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = HASH_CODE * result + id.hashCode();
        result = HASH_CODE * result + payload.hashCode();
        result = HASH_CODE * result + author.hashCode();
        result = HASH_CODE * result + updatedAt.hashCode();
        return result;
    }

}
