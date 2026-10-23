package org.psframework.network;

// Imports
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import java.util.HashMap;
import java.util.Map;

public final class PSPacketHandler {
    private final String packageName;
    private final Map<Integer, String> packets;
    private final Map<Integer, PSReceivePacket<?, ?, ?>> packetClasses;

    /**
     * Creates a new package manager.
     * @param packageName The required package name for searching for packets.
     */
    public PSPacketHandler(String packageName) {
        this.packageName = packageName;
        this.packets = new HashMap<>();
        this.packetClasses = new HashMap<>();
    }

    /**
     * Register's the packets.
     */
    public void registerPackets() {
        this.packets.clear();
        try (ScanResult scan = new ClassGraph().enableClassInfo().enableAnnotationInfo().acceptPackages(this.packageName).scan()) {
            for (var info : scan.getClassesWithAnnotation(PSPacket.class.getName())) {
                Class<?> packetClass = info.loadClass();
                PSPacket annotation = packetClass.getAnnotation(PSPacket.class);
                if (annotation == null || annotation.disabled())
                    continue;

                String previous = this.packets.putIfAbsent(annotation.id(), packetClass.getSimpleName());
                if (previous != null)
                    throw new IllegalStateException("Duplicate packet ID " + annotation.id() + " in package " + this.packageName);

                this.packetClasses.put(annotation.id(), (PSReceivePacket<?, ?, ?>) packetClass.getDeclaredConstructor().newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the packet class from its ID.
     *
     * @param opcode the packet's ID
     * @return the registered packet class, or null if not found
     */
    public PSReceivePacket<?, ?, ?> getPacket(int opcode) {
        return this.packetClasses.get(opcode);
    }

    /**
     * Gets the packet name from packet id.
     * @param opcode The packet's id.
     * @return The packet name.
     */
    public String getPacketName(int opcode) {
        return this.packets.getOrDefault(opcode, String.format("UNKNOWN_PACKET (%s)", opcode));
    }
}