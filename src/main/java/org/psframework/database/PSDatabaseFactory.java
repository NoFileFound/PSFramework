package org.psframework.database;

// Imports
import org.psframework.database.providers.*;

public final class PSDatabaseFactory {
    /**
     * Creates a new database factory.
     */
    private PSDatabaseFactory() {

    }

    /**
     * Creates a new database instance.
     * @param config The database's config.
     * @return The database instance.
     */
    public static PSDatabase create(PSDatabaseConfig config) {
        return switch (config.getProvider()) {
            case PSDatabaseProvider.MONGODB -> new PSMongoDatabase(config);
            case PSDatabaseProvider.MYSQL -> new PSMySQLDatabase(config);
        };
    }
}