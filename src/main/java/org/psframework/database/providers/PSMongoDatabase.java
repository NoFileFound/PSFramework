package org.psframework.database.providers;

// Imports
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import java.util.Collections;
import org.bson.Document;
import org.psframework.database.PSDatabase;
import org.psframework.database.PSDatabaseConfig;

public class PSMongoDatabase implements PSDatabase {
    private final PSDatabaseConfig config;
    private MongoClient client;
    private Datastore datastore;

    /**
     * Creates a new instance of mongo database provider.
     * @param config The database config.
     */
    public PSMongoDatabase(PSDatabaseConfig config) {
        this.config = config;
    }

    /**
     * Gets the morphia's datastore.
     * @return The morphia's datastore.
     */
    public Datastore getDatastore() {
        if (this.datastore == null)
            throw new IllegalStateException("MongoDB is not connected");

        return this.datastore;
    }

    /**
     * Connects to the database.
     */
    @Override
    public synchronized void connect() {
        if (this.isConnected())
            return;

        try {
            MongoClientSettings.Builder builder = MongoClientSettings.builder().applyToClusterSettings(settings -> settings.hosts(Collections.singletonList(new ServerAddress(this.config.getHost(), this.config.getPort()))));
            if (this.config.getUsername() != null && !this.config.getUsername().isBlank()) {
                builder.credential(MongoCredential.createCredential(this.config.getUsername(), this.config.getName(), this.config.getPassword().toCharArray()));
            }

            this.client = MongoClients.create(builder.build());
            this.datastore = Morphia.createDatastore(this.client, this.config.getName());
        } catch (Exception exception) {
            this.disconnect();
            throw new IllegalStateException("Failed to connect to MongoDB", exception);
        }
    }

    /**
     * Disconnects from the database.
     */
    @Override
    public synchronized void disconnect() {
        if (this.client != null) {
            this.client.close();
            this.client = null;
            this.datastore = null;
        }
    }

    /**
     * Checks if the database is connected.
     * @return True if the database is connected or else False.
     */
    @Override
    public boolean isConnected() {
        if (this.client == null || this.datastore == null)
            return false;

        try {
            this.datastore.getDatabase().runCommand(new Document("ping", 1));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * Gets the mongodb's url.
     * @return The mongodb's url.
     */
    @Override
    public String getConnectionUrl() {
        return String.format("mongodb://%s:%s@%s:%d/%s", this.config.getUsername(), this.config.getPassword(), this.config.getHost(), this.config.getPort(), this.config.getName());
    }

    /**
     * Gets the next counter id.
     * @param variable The variable to count.
     * @return The next id by variable if exist or else 0.
     */
    public int getNextCounterId(String variable) {
        MongoCollection<Document> counters = this.client.getDatabase(config.getName()).getCollection("counters");
        Document result = counters.findOneAndUpdate(new Document("_id", variable), new Document("$inc", new Document("sequence", 1)), new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER));
        return result.getInteger("sequence");
    }
}