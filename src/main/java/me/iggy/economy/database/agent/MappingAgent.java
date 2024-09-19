package me.iggy.economy.database.agent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import java.lang.reflect.Modifier;

public class MappingAgent {

    private final Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
    private final JsonWriterSettings settings = JsonWriterSettings.builder()
            .int64Converter((aLong, writer) -> writer.writeNumber(aLong.toString()))
            .build();

    public <T> Document map(T entity) {
        return Document.parse(gson.toJson(entity));
    }

    public <T> T fromMapped(Document document, Class<T> clazz) {
        return gson.fromJson(document.toJson(settings), clazz);
    }

}