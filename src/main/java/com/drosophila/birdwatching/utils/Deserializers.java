package com.drosophila.birdwatching.utils;

import com.drosophila.birdwatching.enums.Age;
import com.drosophila.birdwatching.enums.Sex;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class Deserializers {

    public static class AgeDeserializer extends JsonDeserializer<Age> {

        @Override
        public Age deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return Age.fromString(jsonParser.getText());
        }
    }

    public static class SexDeserializer extends JsonDeserializer<Sex> {

        @Override
        public Sex deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return Sex.fromString(jsonParser.getText());
        }
    }
}
