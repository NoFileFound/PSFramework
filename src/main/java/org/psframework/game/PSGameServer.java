package org.psframework.game;

// Imports
import java.util.ArrayList;
import org.psframework.admin.PSAdminConfig;
import org.psframework.database.PSDatabase;
import org.psframework.database.PSDatabaseConfig;
import org.psframework.database.PSDatabaseFactory;
import org.psframework.logging.PSLogger;

public abstract class PSGameServer {
    protected final PSLogger logger;
    protected final PSDatabase database;
    protected final ArrayList<Integer> ports;
    protected boolean running;

    /**
     * Creates a new game server.
     * @param gamePorts The game server's ports.
     * @param databaseConfig The database's configuration.
     * @param adminConfig The admin panel's configuration.
     * @param logger The game server's logger.
     */
    protected PSGameServer(ArrayList<Integer> gamePorts, PSDatabaseConfig databaseConfig, PSAdminConfig adminConfig, PSLogger logger) {
        this.ports = gamePorts;
        this.database = PSDatabaseFactory.create(databaseConfig);
        this.logger = logger;
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
     * Gets the server logger.
     * @return The server's logger.
     */
    public PSLogger getLogger() {
        return this.logger;
    }

    /**
     * Loads the server's database.
     */
    public abstract void loadDatabase();

    /**
     * Saves the server's database.
     */
    public abstract void saveDatabase();
}