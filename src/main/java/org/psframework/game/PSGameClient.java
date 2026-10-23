package org.psframework.game;

// Imports
import org.psframework.network.PSSendPacket;

public abstract class PSGameClient {
    protected final PSGameServer server;

    /**
     * Creates a new game client.
     * @param server The game server instance.
     */
    protected PSGameClient(PSGameServer server) {
        this.server = server;
    }

    /**
     * Gets the game's client ip address.
     * @return The game client's ip address.
     */
    public abstract String getIpAddress();

    /**
     * Gets the game server.
     * @return The game server.
     */
    public PSGameServer getServer() {
        return server;
    }

    /**
     * Sends a data to the client.
     * @param data The data to send.
     */
    public abstract void sendData(PSSendPacket data);

    /**
     * Disconnects the client from the game.
     */
    public abstract void disconnect();
}