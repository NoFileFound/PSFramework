package org.psframework.network;

public class PSReceivePacketException extends RuntimeException {
    public PSReceivePacketException(String message) {
        super(message);
    }

    public PSReceivePacketException(String message, Throwable cause) {
        super(message, cause);
    }

    public PSReceivePacketException(Throwable cause) {
        super(cause);
    }
}