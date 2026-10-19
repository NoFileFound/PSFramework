package org.psframework.utils;

// Imports
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public final class PSHMAC {
    /**
     * Computes the HMAC-SHA1 of the given data using the specified key.
     * @param data The given string to compute.
     * @param key The given key.
     * @return The HMAC-SHA1 as a lowercase hex string.
     */
    public static String getHMAC1(String data, String key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(byte b : rawHmac)
            sb.append(String.format("%02x", b & 0xff));

        return sb.toString();
    }

    /**
     * Computes the HMAC-SHA1 of the given data using the specified key.
     * @param data The given string to compute.
     * @param key The given key as a byte array.
     * @return The HMAC-SHA1 as a lowercase hex string.
     */
    public static String getHMAC1(String data, byte[] key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(byte b : rawHmac)
            sb.append(String.format("%02x", b & 0xff));

        return sb.toString();
    }

    /**
     * Computes the HMAC-SHA256 of the given data using the specified key.
     * @param data The given string to compute.
     * @param key The given key.
     * @return The HMAC-SHA1 as a lowercase hex string.
     */
    public static String getHMAC256(String data, String key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(byte b : rawHmac)
            sb.append(String.format("%02x", b & 0xff));

        return sb.toString();
    }

     /**
     * Computes the HMAC-SHA256 of the given data using the specified key.
     * @param data The given string to compute.
     * @param key The given key as a byte array.
     * @return The HMAC-SHA1 as a lowercase hex string.
     */
    public static String getHMAC256(String data, byte[] key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for(byte b : rawHmac)
            sb.append(String.format("%02x", b & 0xff));

        return sb.toString();
    }
}