package com.telegram.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.telegram.common.util.DateTimeUtil.LOCAL_DATE_TIME_ISO_PATTERN;

/**
 * Local Date Time Serializer.
 *
 * @author Volodymyr Lykhvar
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_ISO_PATTERN);

    @Override
    public void serialize(LocalDateTime value, JsonGenerator g, SerializerProvider provider) throws IOException {
        var dateWithSystemZone = value.atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now()));
        g.writeString(dateWithSystemZone.format(FORMATTER));
    }
}
