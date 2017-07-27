package com.hubrickchallenge.android.model.mapper;

import javax.annotation.Nullable;

/**
 * Common interface for all the mappers mapping the firebase responses to our view model.
 */
public interface Mapper<FROM, TO> {

    @Nullable
    TO map(FROM from);

}
