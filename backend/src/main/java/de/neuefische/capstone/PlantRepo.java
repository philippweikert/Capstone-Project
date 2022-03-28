package de.neuefische.capstone;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepo extends MongoRepository<Plant, String> {
}
