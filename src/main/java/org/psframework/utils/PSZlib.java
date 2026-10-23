package org.psframework.utils;

// Imports
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public final class PSZlib {
    /**
     * Compresses data using the zlib format.
     * @param data The data to compress.
     * @return The compressed data.
     */
    public static byte[] compress(byte[] data) {
        if (data == null || data.length == 0)
            return new byte[0];

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            while (!deflater.finished()) {
                int length = deflater.deflate(buffer);
                output.write(buffer, 0, length);
            }

            return output.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to compress the data.", e);
        } finally {
            deflater.end();
        }
    }

    /**
     * Decompresses zlib-compressed data.
     * @param data The compressed data.
     * @return The decompressed data.
     */
    public static byte[] decompress(byte[] data) {
        if (data == null || data.length == 0)
            return new byte[0];

        Inflater inflater = new Inflater();
        inflater.setInput(data);
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            while (!inflater.finished()) {
                int length = inflater.inflate(buffer);
                if (length > 0) {
                    output.write(buffer, 0, length);
                } else if (inflater.needsDictionary()) {
                    throw new IllegalArgumentException("The compressed data requires a dictionary.");
                } else if (inflater.needsInput()) {
                    throw new IllegalArgumentException("The compressed data is incomplete or invalid.");
                } else {
                    throw new IllegalArgumentException("Unable to decompress the data.");
                }
            }

            return output.toByteArray();
        } catch (DataFormatException e) {
            throw new IllegalArgumentException("Invalid zlib data.", e);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to decompress data.", e);
        } finally {
            inflater.end();
        }
    }
}