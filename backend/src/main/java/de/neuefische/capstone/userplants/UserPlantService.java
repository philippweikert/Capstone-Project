package de.neuefische.capstone.userplants;

import de.neuefische.capstone.plantcomponents.Plant;
import de.neuefische.capstone.plantcomponents.PlantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPlantService {

    private final PlantRepo plantRepo;

    public Optional<Plant> getPlant(String scientificName) {
        return plantRepo.findByScientificName(scientificName);
    }

    public Optional<Plant> getPlants(String nonScientificName) {
        return plantRepo.findByNonScName(nonScientificName);
    }

    public Optional<Plant> getMorePlants(String location) {
        return plantRepo.findByLocation(location);
    }

    public List<Plant> getAllPlants(Principal principal) {
        return plantRepo.findAllByUser(principal.getName());
    }
}
