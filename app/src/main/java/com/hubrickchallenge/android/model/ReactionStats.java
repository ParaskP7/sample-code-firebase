package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class ReactionStats {

    @PropertyName("counts")
    private Counts counts;

    public ReactionStats() {
        // Default constructor required for calls to DataSnapshot.getValue(ReactionStats.class)
    }

    public Counts getCounts() {
        return counts;
    }

    @Override
    public String toString() {
        return "ReactionStats{" +
                "counts=" + counts +
                '}';
    }

}
