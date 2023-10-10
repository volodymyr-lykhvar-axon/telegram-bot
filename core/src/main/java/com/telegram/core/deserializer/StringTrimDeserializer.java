package com.telegram.core.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import java.io.IOException;

/**
 * String Trim Deserializer.
 *
 * @author Volodymyr Lykhvar
 */
public class StringTrimDeserializer extends StringDeserializer {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var result = super.deserialize(p, ctxt);
        return result == null ? null : result.trim();
    }
}
