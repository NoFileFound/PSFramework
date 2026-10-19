package org.psframework.utils;

// Imports
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class PSBase64 {
    /**
     *  Encodes a string into Base64.
     * @param value The value to encode.
     * @return The base64 content as a string.
     */
    public static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decodes a base64 into a string.
     * @param value The base64 string to decode.
     * @return The string after decoding from base64.
     */
    public static String decode(String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }

    /**
     * Encodes a byte array to base64.
     * @param data The byte array to encode.
     * @return The base64 encoded as a byte array.
     */
    public static byte[] encode(byte[] data) {
        return Base64.getEncoder().encode(data);
    }

    /**
     * Decodes a base64 to byte array.
     * @param data The base64 as a byte array to decode.
     * @return The byte array after decoding from base64.
     */
    public static byte[] decode(byte[] data) {
        return Base64.getDecoder().decode(data);
    }
}