package org.psframework.network;

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
    int getOpcode() {
        return this.opcode;
    }

    /**
     * Function to send the packet.
     * @param data The data to send.
     * @throws PSSendPacketException Exception if cannot send the data.
     */
    protected abstract void sendPacket(byte[] data) throws PSSendPacketException;
}