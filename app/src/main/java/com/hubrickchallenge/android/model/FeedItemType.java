package com.hubrickchallenge.android.model;

import timber.log.Timber;

public enum FeedItemType {

    ADD("ADD"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private final String type;

    FeedItemType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static FeedItemType fromType(final String type) {
        Timber.v("Fetching %s enum for type: %s", FeedItemType.class.getSimpleName(), type);
        for (final FeedItemType feedItemType : FeedItemType.values()) {
            if (type.equals(feedItemType.getType())) {
                return feedItemType;
            }
        }
        throw new IllegalArgumentException("Type '" + type + "' does not correspond to any " +
                FeedItemType.class.getSimpleName() + " enum.");
    }

}
