package com.hubrickchallenge.android.model;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.PropertyName;

@IgnoreExtraProperties
public class Author {

    @PropertyName("name")
    private String name;
    @PropertyName("avatarImage")
    private AvatarImage avatarImage;
    @PropertyName("displayName")
    private String displayName;

    public Author() {
        // Default constructor required for calls to DataSnapshot.getValue(Author.class)
    }

    public String getName() {
        return name;
    }

    public AvatarImage getAvatarImage() {
        return avatarImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", avatarImage=" + avatarImage +
                ", displayName='" + displayName + '\'' +
                '}';
    }

}
