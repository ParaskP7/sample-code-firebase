package com.hubrickchallenge.android;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * A {@link RobolectricTestRunner} that allows us to set robolectric configuration in one place instead of setting it in each test class via
 * {@link Config}.
 */
public class PreconfiguredRobolectricTestRunner extends RobolectricTestRunner {

    private static final int SDK_API_LEVEL_TO_EMULATE = 25;

    /**
     * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file and res directory by
     * default. Use the {@link Config} annotation to configure.
     *
     * @param testClass The test class to be run.
     * @throws InitializationError If junit says so.
     */
    public PreconfiguredRobolectricTestRunner(final Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    @Override
    protected Config buildGlobalConfig() {
        return new Config.Builder()
                .setSdk(SDK_API_LEVEL_TO_EMULATE)
                .setManifest(Config.NONE)
                .build();
    }

}
