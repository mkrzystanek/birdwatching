package com.drosophila.birdwatching.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
public class Trips {

    @Id
    @Setter
    private ObjectId _id;
    @Setter @Getter
    private List<Observations> observations;
    @Setter @Getter
    private String date;
    @Setter @Getter
    private String localization;
    @Setter @Getter
    private String Biome;

    public String get_id() {
        return _id.toHexString();
    }
}
