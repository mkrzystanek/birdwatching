package com.drosophila.birdwatching;

import com.drosophila.birdwatching.enums.Age;
import com.drosophila.birdwatching.enums.Sex;
import com.drosophila.birdwatching.models.Observations;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public final class TestUtils {

    private TestUtils() {}

    public static List<Observations> getObservations() {
        Observations observation1 = Observations.builder()
                ._id(new ObjectId(new Date()))
                .age(Age.FLEDGLING)
                .sex(Sex.FEMALE)
                .habitat("Forest")
                .localization("Europe")
                .notes("Rare")
                .species("Pica pica")
                .weather("Rainstorm")
                .build();
        Observations observation2 = Observations.builder()
                ._id(new ObjectId(new Date()))
                .age(Age.ADULT)
                .sex(Sex.UNKNOWN)
                .habitat("City park")
                .localization("Warsaw")
                .notes("Common")
                .species("Corvus")
                .weather("Sunny")
                .build();

        return List.of(observation1, observation2);
    }
}
