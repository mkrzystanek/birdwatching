package com.drosophila.birdwatching.repositories;

import com.drosophila.birdwatching.models.Observations;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObservationsRepository extends MongoRepository<Observations, String> {
    Observations findBy_id(ObjectId _id);
}
