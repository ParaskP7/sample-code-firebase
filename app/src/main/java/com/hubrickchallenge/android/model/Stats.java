package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class Stats {

    @PropertyName("commentStats")
    private CommentStats commentStats;
    @PropertyName("reactionStats")
    private ReactionStats reactionStats;

    public Stats() {
        // Default constructor required for calls to DataSnapshot.getValue(Stats.class)
    }

    public Stats(CommentStats commentStats, ReactionStats reactionStats) {
        this.commentStats = commentStats;
        this.reactionStats = reactionStats;
    }

    public CommentStats getCommentStats() {
        return commentStats;
    }

    public ReactionStats getReactionStats() {
        return reactionStats;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "commentStats=" + commentStats +
                ", reactionStats=" + reactionStats +
                '}';
    }

}
