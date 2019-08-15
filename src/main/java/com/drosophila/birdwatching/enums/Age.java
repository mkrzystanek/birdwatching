package com.drosophila.birdwatching.enums;

import com.drosophila.birdwatching.utils.Deserializers;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Arrays;

@JsonDeserialize(using = Deserializers.AgeDeserializer.class)
public enum Age {
    HATCHLING("Hatchling"),
    NESTLING("Nestling"),
    FLEDGLING("Fledgling"),
    JUVENILE("Juvenile"),
    SUBADULT("Subadult"),
    ADULT("Adult"),
    UNKNOWN("Unknown");

    private String value;

    Age(String value) {
        this.value = value;
    }

    public static Age fromString(String stringValue) {
        return Arrays.stream(values())
                .filter(value -> value.value.equalsIgnoreCase(stringValue))
                .findFirst()
                .orElse(UNKNOWN);
    }

    public String value() {
        return value;
    }
}
