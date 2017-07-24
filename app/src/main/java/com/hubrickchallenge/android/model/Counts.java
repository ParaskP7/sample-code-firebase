package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class Counts {

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

    public Long getLike() {
        return like;
    }

    @Override
    public String toString() {
        return "Counts{" +
                "share=" + share +
                ", like=" + like +
                '}';
    }

}
