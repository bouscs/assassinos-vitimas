package org.david.assassinos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.david.assassinos.db.Database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class App {
    public static Properties props;

    public static MongoClient mongoClient;

    public static Database db;

    public static void loadProps() {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "application.properties";

            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));

            App.props = appProps;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void databaseConnect() {
        String connectionString = App.props.getProperty("mongodb.uri");
        try {
            App.mongoClient = MongoClients.create(connectionString);

            App.db = new Database(App.mongoClient, App.props.getProperty("mongodb.database"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        loadProps();
        databaseConnect();
        new Janela();
    }
}