package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

@IgnoreExtraProperties
public class Stats extends RealmObject {

    @PropertyName("commentStats")
    private CommentStats commentStats;
    @PropertyName("reactionStats")
    private ReactionStats reactionStats;

    public Stats() {
        // Default constructor required for calls to DataSnapshot.getValue(Stats.class)
    }

    public CommentStats getCommentStats() {
        return commentStats;
    }

    public void setCommentStats(CommentStats commentStats) {
        this.commentStats = commentStats;
    }

    public ReactionStats getReactionStats() {
        return reactionStats;
    }

    public void setReactionStats(ReactionStats reactionStats) {
        this.reactionStats = reactionStats;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "commentStats=" + commentStats +
                ", reactionStats=" + reactionStats +
                '}';
    }

}
