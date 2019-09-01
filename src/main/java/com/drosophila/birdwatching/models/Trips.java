package com.drosophila.birdwatching.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@Document(collection = "trips")
@ToString
public class Trips {

    @Id
    @Setter
    private ObjectId _id;
    @Getter @Setter
    @DBRef(db = "observations")
    private List<Observations> observations;
    @Setter @Getter
    private String date;
    @Setter @Getter
    private String localization;
    @Setter @Getter
    private String biome;

    public String get_id() {
        return _id.toHexString();
    }
}
