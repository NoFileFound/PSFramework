package org.psframework.utils;

// Imports
import java.nio.charset.StandardCharsets;

public final class PSXOR {
    /**
     * Performs a xor encryption on the given data with the given key.
     * @param data The given data.
     * @param key The given key.
     * @return Xor-ed array.
     */
    public static byte[] performXor(byte[] data, byte[] key) {
        byte[] result = new byte[data.length];
        if (key.length == 0)
            throw new IllegalArgumentException("Key cannot be empty");

        for(int i = 0; i < data.length; i++)
            result[i] = (byte) (data[i] ^ key[i % key.length]);

        return result;
    }

    /**
     * Performs a xor encryption on the given data with the given key.
     * @param data The given data.
     * @param key The given key.
     * @return Xor-ed array.
     */
    public static byte[] performXor(byte[] data, String key) {
        return performXor(data, key.getBytes(StandardCharsets.UTF_8));
    }
}