package org.psframework.database.providers;

// Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.psframework.database.PSDatabase;
import org.psframework.database.PSDatabaseConfig;

public class PSMySQLDatabase implements PSDatabase {
    private final PSDatabaseConfig config;
    private Connection connection;

    /**
     * Creates a new instance of mysql database provider.
     * @param config The database config.
     */
    public PSMySQLDatabase(PSDatabaseConfig config) {
        this.config = config;
    }

    /**
     * Gets the MySQL connection.
     * @return The MySQL connection.
     */
    public Connection getConnection() {
        if (!this.isConnected())
            throw new IllegalStateException("MySQL is not connected");

        return this.connection;
    }

    /**
     * Connects to the database.
     */
    @Override
    public synchronized void connect() {
        if (this.isConnected())
            return;

        try {
            this.connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", this.config.getHost(), this.config.getPort(), this.config.getName()), this.config.getUsername(), this.config.getPassword());
        } catch (SQLException exception) {
            this.disconnect();
            throw new IllegalStateException("Failed to connect to MySQL", exception);
        }
    }

    /**
     * Disconnects from the database.
     */
    @Override
    public synchronized void disconnect() {
        if (this.connection == null)
            return;

        try {
            this.connection.close();
        } catch (SQLException exception) {
            throw new IllegalStateException("Failed to close MySQL connection", exception);
        } finally {
            this.connection = null;
        }
    }

    /**
     * Checks if the database is connected.
     * @return True if the database is connected or else False.
     */
    @Override
    public boolean isConnected() {
        if (this.connection == null)
            return false;

        try {
            return !this.connection.isClosed() && this.connection.isValid(2);
        } catch (SQLException exception) {
            return false;
        }
    }

    /**
     * Gets the mongodb's url.
     * @return The mongodb's url.
     */
    @Override
    public String getConnectionUrl() {
        return String.format("jdbc:mysql://%s:%d/%s", this.config.getHost(), this.config.getPort(), this.config.getName());
    }

    /**
     * Gets the next counter id.
     * @param variable The variable to count.
     * @return The next id by variable if exist or else 0.
     */
    @Override
    public synchronized int getNextCounterId(String variable) {
        String sql = """
            INSERT INTO counters (variable, sequence_value)
            VALUES (?, LAST_INSERT_ID(1))
            ON DUPLICATE KEY UPDATE
                sequence_value = LAST_INSERT_ID(sequence_value + 1)
            """;

        try (PreparedStatement statement = this.getConnection().prepareStatement(sql)) {
            statement.setString(1, variable);
            statement.executeUpdate();
            try (Statement query = this.connection.createStatement(); ResultSet result = query.executeQuery("SELECT LAST_INSERT_ID()")) {
                if (result.next())
                    return result.getInt(1);
            }
        } catch (SQLException exception) {
            throw new IllegalStateException("Failed to generate counter ID: " + variable, exception);
        }

        throw new IllegalStateException("Failed to generate counter ID: " + variable);
    }
}