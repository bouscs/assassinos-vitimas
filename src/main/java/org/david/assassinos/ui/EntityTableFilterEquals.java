package org.david.assassinos.ui;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.david.assassinos.App;
import org.david.assassinos.db.Entity;

import java.util.ArrayList;

public class EntityTableFilterEquals<T extends Entity> extends EntityTableFilter<T> {
    public EntityTableFilterEquals(MongoCollection<T> collection, String property, String value) {
        super(() -> collection.find(Filters.eq(property, value)).into(new ArrayList<>()));
    }
}
