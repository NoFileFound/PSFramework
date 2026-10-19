package org.psframework.admin;

public class PSAdminConfig {
    private final int port;
    private final String username;
    private final String password;

    /**
     * Creates a new instance of admin panel config.
     * @param port The admin panel's port.
     * @param username The admin panel's username.
     * @param password The admin panel's password.
     */
    public PSAdminConfig(int port, String username, String password) {
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the admin panel's port.
     * @return The admin panel's port.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Gets the admin panel's username.
     * @return The admin panel's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the admin panel's password.
     * @return The admin panel's password.
     */
    public String getPassword() {
        return this.password;
    }
}