package org.psframework.network;

// Imports
import org.psframework.utils.PSByteArray;

public abstract class PSSendPacket {
    private final int opcode;

    /**
     * Creates a new send packet.
     * @param opcode The packet's id.
     */
    protected PSSendPacket(int opcode) {
        this.opcode = opcode;
    }

    /**
     * Gets the packet's id.
     * @return The packet's id.
     */
    public int getOpcode() {
        return this.opcode;
    }

    /**
     * Function to build the packet into bytearray.
     * @throws PSSendPacketException Exception if cannot build the packet.
     */
    public abstract PSByteArray build() throws PSSendPacketException;
}