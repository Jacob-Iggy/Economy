package me.iggy.economy.database;

import com.mongodb.MongoClient;
import lombok.Getter;
import me.iggy.economy.Economy;
import me.iggy.economy.database.collection.WrappedCollection;


@Getter
public class DatabaseHandler {

    private final MongoClient mongoClient;

    public DatabaseHandler() {
        mongoClient = MongoConnector.MongoConnectorBuilder.asURI(Economy.getInstance().getConfig().getString("database.mongo.uri")).toClient();
    }

    public <T> WrappedCollection<T> createCollection(String collection, Class<T> clazz) {
        return new WrappedCollection<>(getMongoClient().getDatabase(Economy.getInstance().getConfig().getString("database.mongo.db")).getCollection(collection), clazz);
    }

}