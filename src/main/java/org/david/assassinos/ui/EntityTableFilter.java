package org.david.assassinos.ui;

import com.mongodb.client.MongoCollection;
import org.bson.conversions.Bson;
import org.david.assassinos.App;
import org.david.assassinos.db.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class EntityTableFilter<T extends Entity> {
    public Supplier<List<T>> findFunction;

    public EntityTableFilter(MongoCollection<T> collection) {
        this.findFunction = () -> collection.find().into(new ArrayList<>());
    }
    public EntityTableFilter(Supplier<List<T>> findFunction) {
        this.findFunction = findFunction;
    }

    public List<T> find() {
        return findFunction.get();
    }
}
