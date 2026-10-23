package org.psframework.network;

// Imports
import org.psframework.game.PSGameClient;
import org.psframework.game.PSGameServer;
import org.psframework.utils.PSByteArray;

public abstract class PSReceivePacket<Server extends PSGameServer, Client extends PSGameClient, ByteArray extends PSByteArray> {
    /**
     * Handles the received packet.
     * @param gameServer The game's server.
     * @param clientSession The game's session client.
     * @param header The packet's header (if it has).
     * @param data The packet's data.
     * @throws PSReceivePacketException Exception when handling the received packet.
     */
    public abstract void handle(Server gameServer, Client clientSession, ByteArray header, ByteArray data) throws PSReceivePacketException;

    /**
     * Dispatches this packet to its handler.
     * @param gameServer The game's server.
     * @param clientSession The game's session client.
     * @param header The packet's header (if it has).
     * @param data The packet's data.
     * @throws PSReceivePacketException Exception when handling the received packet.
     */
    @SuppressWarnings("unchecked")
    public final void dispatch(PSGameServer gameServer, PSGameClient clientSession, PSByteArray header, PSByteArray data) throws PSReceivePacketException {
        handle((Server) gameServer, (Client) clientSession, (ByteArray) header, (ByteArray) data);
    }
}