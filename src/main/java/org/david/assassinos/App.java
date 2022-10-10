package org.david.assassinos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class App {
    public static Properties props;

    public static void loadProps() {
        try {
            String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String appConfigPath = rootPath + "application.properties";

            Properties appProps = new Properties();
            appProps.load(new FileInputStream(appConfigPath));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadProps();
        new Janela();
    }
}