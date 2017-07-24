package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

@IgnoreExtraProperties
public class Counts extends RealmObject {

    @PropertyName("SHARE")
    private Long share;
    @PropertyName("LIKE")
    private Long like;

    public Counts() {
        // Default constructor required for calls to DataSnapshot.getValue(Counts.class)
    }

    public Long getShare() {
        return share;
    }

    public void setShare(Long share) {
        this.share = share;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "Counts{" +
                "share=" + share +
                ", like=" + like +
                '}';
    }

}
