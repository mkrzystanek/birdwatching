package com.drosophila.birdwatching.enums;

import com.drosophila.birdwatching.utils.Deserializers;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;

@JsonDeserialize(using = Deserializers.SexDeserializer.class)
public enum Sex {
    MALE("male"),
    FEMALE("female"),
    UNKNOWN("unknown");

    private String value;

    Sex(String value) {
        this.value = value;
    }

    public static Sex fromString(String stringValue) {
        return Arrays.stream(values())
                .filter(value -> value.value.equalsIgnoreCase(stringValue))
                .findFirst()
                .orElse(UNKNOWN);
    }

    public String value() { return value; }
}
