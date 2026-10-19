package org.psframework.database;

public class PSDatabaseConfig {
    private final PSDatabaseProvider provider;
    private final String host;
    private final int port;
    private final String name;
    private final String username;
    private final String password;

    /**
     * Creates a new database connection config.
     * @param provider The database's provider type.
     * @param host The database's host.
     * @param port The database's port.
     * @param name The database's name.
     * @param username The database's root name.
     * @param password The database's root password.
     */
    public PSDatabaseConfig(PSDatabaseProvider provider, String host, int port, String name, String username, String password) {
        this.provider = provider;
        this.host = host;
        this.port = port;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the database's provider.
     * @return The database's provider.
     */
    public PSDatabaseProvider getProvider() {
        return provider;
    }

    /**
     * Gets the database's host name.
     * @return The database's host name.
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets the database's port.
     * @return The database's port.
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the database's name.
     * @return The database's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the database's root username.
     * @return The database's root username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the database's root password.
     * @return The database's root password.
     */
    public String getPassword() {
        return password;
    }
}