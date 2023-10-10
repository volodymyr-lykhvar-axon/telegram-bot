package com.telegram.core.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.telegram.common.util.DateTimeUtil.toLocalDateTime;

/**
 * Local Date Time Deserializer.
 *
 * @author Volodymyr Lykhvar
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private final DateDeserializers.DateDeserializer dateDeserializer;

    public LocalDateTimeDeserializer() {
        dateDeserializer = new DateDeserializers.DateDeserializer();
    }

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return toLocalDateTime(dateDeserializer.deserialize(p, ctxt));
    }
}