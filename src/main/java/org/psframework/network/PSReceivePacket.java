package org.psframework.network;

// Imports
import org.psframework.game.PSGamePlayer;
import org.psframework.game.PSGameServer;
import org.psframework.utils.PSByteArray;

public abstract class PSReceivePacket {
    /**
     * Handles the received packet.
     * @param gameServer The game's server.
     * @param gamePlayer The game's player.
     * @param header The packet's header (if it has).
     * @param data The packet's data.
     */
    public abstract void handle(PSGameServer gameServer, PSGamePlayer gamePlayer, PSByteArray header, PSByteArray data) throws PSReceivePacketException;
}