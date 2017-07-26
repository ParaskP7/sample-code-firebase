package com.hubrickchallenge.android.util;

import java.util.Random;

public class GeneralUtil {

    public static final String SPACE = " ";
    public static final int HASH_CODE = 31;

    private static final Random RANDOM = new Random();

    private GeneralUtil() {
        throw new AssertionError();
    }

    /**
     * Returns a pseudo-random number between min and max, inclusive. The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value. Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see Random#nextInt(int)
     */
    public static int randomInt(final int min, final int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }

}
