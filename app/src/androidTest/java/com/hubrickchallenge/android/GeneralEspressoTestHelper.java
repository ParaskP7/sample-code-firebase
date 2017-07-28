package com.hubrickchallenge.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.rule.ActivityTestRule;

public class GeneralEspressoTestHelper {

    protected void launchActivity(final ActivityTestRule activityTestRule, final Bundle extras) {
        final Intent intent = new Intent();
        intent.putExtras(extras);
        activityTestRule.launchActivity(intent);
    }

    protected Bundle getExtras() {
        final Bundle extras = new Bundle();
        return extras;
    }

}
