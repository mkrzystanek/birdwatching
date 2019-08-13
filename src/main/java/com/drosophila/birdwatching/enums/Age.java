package com.drosophila.birdwatching.enums;

import java.util.Arrays;

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
