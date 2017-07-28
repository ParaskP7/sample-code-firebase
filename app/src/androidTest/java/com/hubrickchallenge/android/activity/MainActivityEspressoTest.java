package com.hubrickchallenge.android.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.hubrickchallenge.android.GeneralEspressoTestHelper;
import com.hubrickchallenge.android.R;
import com.hubrickchallenge.android.activity.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest extends GeneralEspressoTestHelper {

    private static final boolean INITIAL_TOUCH_MODE = false;
    private static final boolean LAUNCH_ACTIVITY = false;

    @Rule
    @SuppressFBWarnings("URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class, INITIAL_TOUCH_MODE, LAUNCH_ACTIVITY);

    @Test
    public void verifyActivityTitleIsShown() {
        launchActivity(activityTestRule, getExtras());
        onView(withText(R.string.activity_label))
                .check(matches(isDisplayed()));
    }

}
