package org.psframework.utils;

// Imports
import java.security.MessageDigest;

public final class PSMD5 {
    /**
     * Computes the MD5 hash of the given byte array.
     * @param data The given byte array.
     * @return The MD5 hash as a lowercase hex string.
     */
    public static String getMd5(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(data);
        StringBuilder sb = new StringBuilder();
        for(byte b : digest)
            sb.append(String.format("%02x", b & 0xff));

        return sb.toString();
    }
}