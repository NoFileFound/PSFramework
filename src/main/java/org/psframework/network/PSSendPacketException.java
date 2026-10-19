package org.psframework.network;

public class PSSendPacketException extends RuntimeException {
    public PSSendPacketException(String message) {
        super(message);
    }

    public PSSendPacketException(String message, Throwable cause) {
        super(message, cause);
    }

    public PSSendPacketException(Throwable cause) {
        super(cause);
    }
}