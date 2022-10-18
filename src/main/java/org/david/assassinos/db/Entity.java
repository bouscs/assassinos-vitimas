package org.david.assassinos.db;

public abstract class Entity {
    private Database db;

    public Entity(Database db) {
        this.db = db;
    }
}
