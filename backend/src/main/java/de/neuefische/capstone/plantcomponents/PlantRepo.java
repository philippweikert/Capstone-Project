package de.neuefische.capstone.plantcomponents;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepo extends MongoRepository<Plant, String> {
    Optional<Plant> findByScientificName(String scientificName);

    Optional<Plant> findByNonScName(String nonScientificName);

    Optional<Plant> findByLocation(String location);

    List<Plant> findAllByUser(String user);
}
