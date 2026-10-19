package org.psframework.game;

public abstract class PSGamePlayer {
    protected final long id;
    protected String name;
    protected PSGameClient client;

    /**
     * Creates a new game player.
     * @param id The game player's id.
     * @param name The game player's name.
     * @param client The game player's client.
     */
    protected PSGamePlayer(long id, String name, PSGameClient client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }

    /**
     * Gets the game player's id.
     * @return The game player's id.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Gets the game player's name.
     * @return The game player's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the game player's client.
     * @return The game player's client.
     */
    public PSGameClient getClient() {
        return this.client;
    }

    /**
     * Gets the game server.
     * @return The game server.
     */
    public PSGameServer getServer() {
        return this.client != null ? this.client.getServer() : null;
    }

    /**
     * Checks if the player is connected.
     * @return True if the player is connected or else False.
     */
    public boolean isConnected() {
        return this.client != null && this.client.isConnected();
    }

    /**
     * Disconnects the player from the game.
     */
    public abstract void disconnect();

    /**
     * Saves the player in the database.
     */
    public abstract void saveDatabase();

    /**
     * Loads the player info from the database.
     */
    public abstract void loadDatabase();
}