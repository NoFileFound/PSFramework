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

    /**
     * Gets the database's connection url.
     * @return The database's connection url.
     */
    String getConnectionUrl();

    /**
     * Gets the next counter id.
     * @param variable The variable to count.
     * @return The next id by variable if exist or else 0.
     */
    int getNextCounterId(String variable);
}