package com.drosophila.birdwatching.repositories;

import com.drosophila.birdwatching.models.Trips;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TripsRepository extends MongoRepository<Trips, String> {
    Trips findBy_id(ObjectId id);
}
