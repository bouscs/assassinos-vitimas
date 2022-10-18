package org.david.assassinos.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Database {
    public MongoDatabase mongoDatabase;

    public MongoCollection<Assassino> assassinos;

    public MongoCollection<Vitima> vitimas;

    public Database(MongoClient client, String databaseName) {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        mongoDatabase = client.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry);

        assassinos = mongoDatabase.getCollection("assassinos", Assassino.class);
        vitimas = mongoDatabase.getCollection("vitimas", Vitima.class);
    }
}
