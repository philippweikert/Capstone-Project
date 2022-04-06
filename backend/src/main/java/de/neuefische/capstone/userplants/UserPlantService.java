package de.neuefische.capstone.userplants;

import de.neuefische.capstone.plantcomponents.Plant;
import de.neuefische.capstone.plantcomponents.PlantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserPlantService {

    private final PlantRepo plantRepo;

    public Plant getPlantByScName(String scientificName, Principal principal) {
        return plantRepo.findByScientificName(scientificName, principal.getName()).orElseThrow(() -> new IllegalArgumentException(scientificName +" ist nicht in der Datenbank!"));
    }

    public Plant getPlantByNonScName(String nonScientificName, Principal principal) {
        return plantRepo.findByNonScName(nonScientificName, principal.getName()).orElseThrow(() -> new IllegalArgumentException(nonScientificName +" ist nicht in der Datenbank!"));
    }

    public Plant getPlantByLocation(String location, Principal principal) {
        return plantRepo.findByLocation(location, principal.getName()).orElseThrow(() -> new IllegalArgumentException("Eine Pflanze die " + location + "stehen muss, ist nicht in der Datenbank!"));
    }

    public List<Plant> getAllPlants(Principal principal) {
        return plantRepo.findAllByUser(principal.getName());
    }
}
