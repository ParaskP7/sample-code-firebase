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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentStats that = (CommentStats) o;

        return count.equals(that.count);

    }

    @Override
    public int hashCode() {
        return count.hashCode();
    }

}
