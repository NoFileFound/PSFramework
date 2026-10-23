package org.psframework.utils;

// Imports
import java.security.SecureRandom;

public final class PSRandom {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * Generates an integer in range.
     *
     * @param min L
     * @param max R
     * @return A random integer in [L, R].
     */
    public static int nextInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min cannot be greater than max");
        }

        return RANDOM.nextInt(min, max + 1);
    }

    /**
     * Generates an integer in range.
     *
     * @param max R
     * @return Random integer in range [1, R].
     */
    public static int nextInt(int max) {
        return RANDOM.nextInt(max);
    }

    /**
     * Generates an integer in range.
     * @param min L
     * @param max R
     * @return Random integer in range [L, R].
     */
    public static long nextLong(long min, long max) {
        if (min > max) {
            throw new IllegalArgumentException("min cannot be greater than max");
        }

        return RANDOM.nextLong(min, max + 1);
    }

    /**
     * Generates a random string of the specified length.
     *
     * @param length The required length.
     * @return A random string.
     */
    public static String nextString(int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            result.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));

        return result.toString();
    }
}