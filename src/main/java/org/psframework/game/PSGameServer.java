package org.psframework.game;

// Imports
import org.psframework.admin.PSAdminConfig;
import org.psframework.database.PSDatabase;
import org.psframework.database.PSDatabaseConfig;
import org.psframework.database.PSDatabaseFactory;

public abstract class PSGameServer {
    protected final PSDatabase database;
    protected final int port;
    protected boolean running;

    /**
     * Creates a new game server.
     */
    protected PSGameServer(int gamePort, PSDatabaseConfig databaseConfig, PSAdminConfig adminConfig) {
        this.port = gamePort;
        this.database = PSDatabaseFactory.create(databaseConfig);
    }

    /**
     * Starts the game server.
     */
    public abstract void start();

    /**
     * Stops the game server.
     */
    public abstract void stop();

    /**
     * Checks if the server is running.
     * @return True if the server is running or else False.
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Gets the server database.
     * @return The server's database.
     */
    public PSDatabase getDatabase() {
        return this.database;
    }

    /**
     * Gets the server's port.
     * @return The server's port.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Loads the server's database.
     */
    public abstract void loadDatabase();
}