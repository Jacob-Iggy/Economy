package me.iggy.economy.database;

import com.mongodb.MongoClient;

public class MongoConnector extends MongoClient {
    public static MongoConnectorBuilder MongoConnectorBuilder = new MongoConnectorBuilder();
}

abstract class MongoConnectorConverter {

    public abstract MongoClient toClient();

    public abstract MongoConnectorBuilder asURI(String string);

    public abstract MongoConnectorBuilder asHost(String host);

    public abstract MongoConnectorBuilder asPort(int port);

    public abstract MongoConnectorBuilder hasAuth(boolean auth);

    public abstract MongoConnectorBuilder asUsername(String username);

    public abstract MongoConnectorBuilder asPassword(String password);

    public abstract MongoConnectorBuilder authDB(String password);
}