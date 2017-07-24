package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

@IgnoreExtraProperties
public class CommentStats extends RealmObject {

    @PropertyName("count")
    private Long count;

    public CommentStats() {
        // Default constructor required for calls to DataSnapshot.getValue(CommentStats.class)
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CommentStats{" +
                "count=" + count +
                '}';
    }

}
