package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

@IgnoreExtraProperties
public class ReactionStats extends RealmObject {

    @PropertyName("counts")
    private Counts counts;

    public ReactionStats() {
        // Default constructor required for calls to DataSnapshot.getValue(ReactionStats.class)
    }

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "ReactionStats{" +
                "counts=" + counts +
                '}';
    }

}
