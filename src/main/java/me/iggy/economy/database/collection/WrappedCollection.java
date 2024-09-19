package me.iggy.economy.database.collection;

import com.google.common.collect.Lists;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Data;
import me.iggy.economy.Economy;
import me.iggy.economy.database.agent.MappingAgent;
import me.iggy.economy.database.models.EntityID;
import org.bson.Document;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Data
public class WrappedCollection<T> {

    private final MongoCollection<Document> collection;
    private final Class<T> clazz;

    private Field idField;

    private MappingAgent agent = new MappingAgent();

    public WrappedCollection(MongoCollection<Document> collection, Class<T> clazz) {
        this.collection = collection;
        this.clazz = clazz;

        init();
    }

    private void init() {
        for (Field declaredField : clazz.getDeclaredFields()) {
            if (declaredField != null && declaredField.isAnnotationPresent(EntityID.class)) {
                this.idField = declaredField;
                break;
            }
        }
    }

    public String getId(T entity) {
        idField.setAccessible(true);
        try {
            return idField.get(entity).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save(T entity) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Economy.getInstance(), () ->
                collection.replaceOne(Filters.eq(idField.getName(), getId(entity)), getAgent().map(entity), new UpdateOptions().upsert(true)));
    }

    public void saveSync(T entity) {
        collection.replaceOne(Filters.eq(idField.getName(), getId(entity)), getAgent().map(entity), new UpdateOptions().upsert(true));
    }

    public void delete(T entity) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Economy.getInstance(), () -> collection.deleteOne(Filters.eq(idField.getName(), getId(entity))));
    }

    public void deleteSync(T entity) {
        collection.deleteOne(Filters.eq(idField.getName(), getId(entity)));
    }

    public Optional<T> load(Object id) {
        final Document document = collection.find(Filters.eq(idField.getName(), id.toString())).first();

        if (document == null) {
            return Optional.empty();
        }

        return Optional.of(getAgent().fromMapped(document, clazz));
    }

    public List<T> loadAll() {
        final List<T> toReturn = Lists.newArrayList();

        final FindIterable<Document> documents = getCollection().find();

        for (Document document : documents) {
            toReturn.add(getAgent().fromMapped(document, clazz));
        }

        return toReturn;
    }

    public Optional<T> loadByEntry(String id, Object object) {
        final Document document = collection.find(Filters.eq(id, object)).first();

        if (document == null) {
            return Optional.empty();
        }

        return Optional.of(getAgent().fromMapped(document, clazz));
    }
}