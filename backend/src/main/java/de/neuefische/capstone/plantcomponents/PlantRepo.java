package de.neuefische.capstone.plantcomponents;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlantRepo extends MongoRepository<Plant, String> {

     List<Plant> findAllByUser(String username);
     Optional<Plant> findByIdAndUser(String id, String username);


}
