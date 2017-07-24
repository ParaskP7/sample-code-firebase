package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class CommentStats {

    @PropertyName("count")
    private Long count;

    public CommentStats() {
        // Default constructor required for calls to DataSnapshot.getValue(CommentStats.class)
    }

    public Long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "CommentStats{" +
                "count=" + count +
                '}';
    }

}
