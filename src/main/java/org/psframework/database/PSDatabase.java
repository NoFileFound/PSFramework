package org.psframework.database;

public interface PSDatabase {
    /**
     * Connects to the database.
     */
    void connect();

    /**
     * Disconnects from the database.
     */
    void disconnect();

    /**
     * Checks if the database is connected.
     * @return True if the database is connected or else False.
     */
    boolean isConnected();
}