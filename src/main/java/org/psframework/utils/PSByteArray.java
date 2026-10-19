package org.psframework.utils;

// Imports
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public class PSByteArray {
    private final List<Byte> buffer;

    /**
     * Creates a new array of bytes.
     */
    public PSByteArray() {
        this(0);
    }

    /**
     * Creates a new array of bytes by given size.
     * @param size The given size.
     */
    public PSByteArray(int size) {
        this.buffer = new ArrayList<>(size);
    }

    /**
     * Creates a new array of bytes by given bytes.
     * @param buffer The given byte array.
     */
    public PSByteArray(byte[] buffer) {
        this.buffer = new ArrayList<>(buffer.length);
        for (byte b : buffer) {
            this.buffer.add(b);
        }
    }

    /**
     * Returns the bytearray.
     * @return The bytearray.
     */
    public List<Byte> getBuffer() {
        return this.buffer;
    }

    /**
     * Returns the length of bytearray.
     * @return The length.
     */
    public int getLength() {
        return this.buffer.size();
    }

    /**
     * Returns the bytes from the bytearray.
     * @return The bytes.
     */
    public synchronized byte[] toByteArray() {
        byte[] result = new byte[buffer.size()];
        for (int i = 0; i < buffer.size(); i++) {
            result[i] = buffer.get(i);
        }
        return result;
    }

    /**
     * Reads a byte from the bytearray.
     * @return A byte.
     */
    public synchronized byte readByte() {
        byte info = this.buffer.getFirst();
        this.buffer.removeFirst();
        return info;
    }

    /**
     * Reads a boolean from the bytearray.
     * @return A boolean.
     */
    public synchronized boolean readBoolean() {
        byte info = this.buffer.getFirst();
        this.buffer.removeFirst();
        return info != 0;
    }

    /**
     * Reads a short from the bytearray.
     * @return A short.
     */
    public short readShort() {
        byte high = this.readByte();
        byte low = this.readByte();
        return (short) ((high << 8) | (low & 0xFF));
    }

    /**
     * Reads an integer from the bytearray.
     * @return An integer.
     */
    public int readInt() {
        byte b1 = this.readByte();
        byte b2 = this.readByte();
        byte b3 = this.readByte();
        byte b4 = this.readByte();
        return ((b1 & 0xFF) << 24) | ((b2 & 0xFF) << 16) | ((b3 & 0xFF) << 8)  | (b4 & 0xFF);
    }

    /**
     * Reads a long from the bytearray.
     * @return A Long.
     */
    public long readLong() {
        byte b1 = this.readByte();
        byte b2 = this.readByte();
        byte b3 = this.readByte();
        byte b4 = this.readByte();
        return ((long) (b1 & 0xFF) << 24) | (long)((b2 & 0xFF) << 16) | (long)((b3 & 0xFF) << 8)  | (long)(b4 & 0xFF);
    }

    /**
     * Reads an unsigned byte from the bytearray.
     * @return An unsigned byte.
     */
    public int readUnsignedByte() {
        return this.readByte() & 0xFF;
    }

    /**
     * Reads an unsigned short from the bytearray.
     * @return An unsigned short.
     */
    public int readUnsignedShort() {
        return (this.readUnsignedByte() << 8) | this.readUnsignedByte();
    }

    /**
     * Reads a bytes from the bytearray.
     * @param length The length.
     * @return An array of bytes.
     */
    public byte[] readBytes(int length) {
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++)
            result[i] = this.readByte();

        return result;
    }

    /**
     * Writes a byte to the bytearray.
     */
    public PSByteArray writeByte(int value) {
        this.buffer.add((byte) value);
        return this;
    }

    /**
     * Writes bytes to the bytearray.
     */
    public PSByteArray writeBytes(byte[] bytes) {
        for (byte b : bytes) {
            this.writeByte(b);
        }
        return this;
    }

    /**
     * Writes a boolean to the bytearray.
     */
    public PSByteArray writeBoolean(boolean value) {
        this.buffer.add((byte)(value ? 1 : 0));
        return this;
    }

    /**
     * Writes a float to the bytearray.
     */
    public PSByteArray writeFloat(float value) {
        this.buffer.add((byte)(Float.floatToIntBits(value)));
        return this;
    }

    /**
     * Writes a short to the bytearray.
     */
    public PSByteArray writeShort(short value) {
        this.writeByte((byte) (value >> 8));
        this.writeByte((byte) (value));
        return this;
    }

    /**
     * Writes an integer to the bytearray.
     */
    public PSByteArray writeInt(int value) {
        this.writeByte((byte) (value >> 24));
        this.writeByte((byte) (value >> 16));
        this.writeByte((byte) (value >> 8));
        this.writeByte((byte) (value));
        return this;
    }

    /**
     * Writes an integer to the bytearray.
     */
    public PSByteArray writeInt(long value) {
        this.writeByte((byte) (value >> 24));
        this.writeByte((byte) (value >> 16));
        this.writeByte((byte) (value >> 8));
        this.writeByte((byte) (value));
        return this;
    }

    /**
     * Writes an unsigned byte to the bytearray.
     */
    public PSByteArray writeUnsignedByte(int value) {
        this.writeByte(value & 0xFF);
        return this;
    }

    /**
     * Writes an unsigned short to the bytearray.
     */
    public PSByteArray writeUnsignedShort(int value) {
        this.writeByte((value >> 8) & 0xFF);
        this.writeByte(value & 0xFF);
        return this;
    }

    /**
     * Writes an unsigned integer to the bytearray.
     */
    public PSByteArray writeUnsignedInt(long value) {
        this.writeByte((int) ((value >> 24) & 0xFF));
        this.writeByte((int) ((value >> 16) & 0xFF));
        this.writeByte((int) ((value >> 8) & 0xFF));
        this.writeByte((int) (value & 0xFF));
        return this;
    }

    /**
     * Converts the bytearray to string.
     */
    @Override
    public String toString() {
        if (this.buffer == null || this.buffer.isEmpty()) {
            return "";
        }

        StringBuilder hexString = new StringBuilder();
        for (byte b : this.buffer) {
            hexString.append(String.format("%02X", b)).append(" ");
        }

        return hexString.toString().trim();
    }
}