package me.iggy.economy.database;

import com.mongodb.*;

public class MongoConnectorBuilder extends MongoConnectorConverter {

    private String uri;
    private String host;
    private int port;
    private boolean auth;
    private String username;
    private String password;
    private String authDB;

    @Override
    public MongoClient toClient() {
        if (uri != null && !uri.isEmpty()) return new MongoClient(new MongoClientURI(uri));
        if (auth) {
            return new MongoClient(new ServerAddress(host, port), MongoCredential.createCredential(username, authDB.isEmpty() ? "admin" : authDB, password.toCharArray()), MongoClientOptions
                    .builder()
                    .readPreference(ReadPreference.primaryPreferred())
                    .retryWrites(true)
                    .sslEnabled(true)
                    .build());
        }
        return new MongoClient(new ServerAddress(host, port));
    }

    @Override
    public MongoConnectorBuilder asURI(String string) {
        this.uri = string;
        return this;
    }

    @Override
    public MongoConnectorBuilder asHost(String host) {
        this.host = host;
        return this;
    }

    @Override
    public MongoConnectorBuilder asPort(int port) {
        this.port = port;
        return this;
    }

    @Override
    public MongoConnectorBuilder hasAuth(boolean auth) {
        this.auth = auth;
        return this;
    }

    @Override
    public MongoConnectorBuilder asUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public MongoConnectorBuilder asPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public MongoConnectorBuilder authDB(String authDB) {
        this.authDB = authDB;
        return this;
    }
}
