package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

import io.realm.RealmObject;

import static com.hubrickchallenge.android.util.GeneralUtil.HASH_CODE;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Stats stats = (Stats) o;

        if (!commentStats.equals(stats.commentStats)) return false;
        return reactionStats.equals(stats.reactionStats);

    }

    @Override
    public int hashCode() {
        int result = commentStats.hashCode();
        result = HASH_CODE * result + reactionStats.hashCode();
        return result;
    }

}
