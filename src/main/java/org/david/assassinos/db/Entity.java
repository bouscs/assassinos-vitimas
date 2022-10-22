package org.david.assassinos.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public abstract class Entity<T> implements Cloneable {
    @BsonIgnore
    protected boolean isDeleted;
    protected ObjectId id;

    private MongoCollection<T> collection;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public Entity(MongoCollection<T> collection) {
        this.collection = collection;

        id = new ObjectId();
        isDeleted = false;
    }

    public abstract void fromRow(Object[] o) throws Exception;

    public abstract Object[] toRow();

    public abstract Object clone();

    public void save() throws Exception {
        Document filterById = new Document("_id", this.getId());

        FindOneAndReplaceOptions findOneAndReplaceOptions = new FindOneAndReplaceOptions()
                .upsert(true);

        try {
            collection.findOneAndReplace(filterById, (T) this, findOneAndReplaceOptions);
        } catch (Exception e) {
            throw new Exception(e);
        }

        isDeleted = false;
    }

    public static <T extends Entity> void saveMany(MongoCollection<T> collection, T[] entities) {
        
    }

    public void delete() {
        Document filterById = new Document("_id", this.getId());

        collection.deleteOne(filterById);

        isDeleted = true;
    }
}
