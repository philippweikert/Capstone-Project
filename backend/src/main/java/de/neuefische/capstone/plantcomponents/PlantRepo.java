package de.neuefische.capstone.plantcomponents;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepo extends MongoRepository<Plant, String> {

    Optional<Plant> findByScientificName(String scientificName, String user);

    Optional<Plant> findByNonScName(String nonScientificName, String user);

    Optional<Plant> findByLocation(String location, String user);

    List<Plant> findAllByUser(String user);
}
