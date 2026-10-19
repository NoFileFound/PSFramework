package org.psframework.game;

// Imports
import java.util.concurrent.atomic.AtomicLong;

public abstract class PSGameClient {
    private static final AtomicLong NEXT_ID = new AtomicLong(1);
    protected final long id;
    protected boolean connected;
    protected final PSGameServer server;

    /**
     * Creates a new game client.
     * @param server The game server instance.
     */
    protected PSGameClient(PSGameServer server) {
        this.id = NEXT_ID.getAndIncrement();
        this.server = server;
    }

    /**
     * Gets the game's client id.
     * @return The game client's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Gets the game server.
     * @return The game server.
     */
    public PSGameServer getServer() {
        return server;
    }

    /**
     * Checks if the client is connected.
     * @return True if the client is connected or else False.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Sends a data to the client.
     * @param data The data to send.
     */
    public abstract void sendData(byte[] data);

    /**
     * Disconnects the client from the game.
     */
    public abstract void disconnect();
}