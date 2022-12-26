package com.ebaykorea.payback.scheduler.support;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GsonUtils {

    private static final Logger log = LoggerFactory.getLogger(GsonUtils.class);
    private static JsonSerializer<LocalDateTime> jsonSerializer = new JsonSerializer<LocalDateTime>() {
        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(TimeUtils.LOCAL_DATE_TIME_FORMATTER.format(localDateTime));
        }
    };

    private static Gson gson = new GsonBuilder()
            .disableHtmlEscaping()
            .registerTypeAdapter(LocalDateTime.class, jsonSerializer)
            .create();

    private static Gson gsonPretty = new GsonBuilder()
            .disableHtmlEscaping()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, jsonSerializer)
            .create();

    public static String toJson(Object o) {
        String result = gson.toJson(o);
        if("string".equals(result))
            return null;
        return result;
    }

    public static String toJsonPretty(Object o) {
        String result = gsonPretty.toJson(o);
        if("string".equals(result))
            return null;
        return result;
    }

    public static <T> T fromJson(String s, Class<T> clazz) {
        try {
            return gson.fromJson(s, clazz);
        } catch(JsonSyntaxException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}