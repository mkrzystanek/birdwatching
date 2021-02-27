package com.drosophila.birdwatching.utils;

import com.drosophila.birdwatching.enums.Age;
import com.drosophila.birdwatching.enums.Sex;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public final class Deserializers {

    private Deserializers() {}

    public static class AgeDeserializer extends JsonDeserializer<Age> {

        @Override
        public Age deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            if (node != null) {
                return Age.fromString(node.asText());
            }
            return null;
        }
    }

    public static class SexDeserializer extends JsonDeserializer<Sex> {

        @Override
        public Sex deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            if (node != null) {
                return Sex.fromString(node.asText());
            }
            return null;
        }
    }
}
