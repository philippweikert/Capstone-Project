package de.neuefische.capstone.plantcomponents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantRepo plantRepo;

    public Plant createPlant (Plant plant, String username){
        plant.setUser(username);
        return plantRepo.save(plant);
    }

    public List<Plant> getAllPlantsByUser(String username) {
        return plantRepo.findAllByUser(username);
    }

    public List<Plant> getMatchingPlant(String searchedPlant, String username) {
        return plantRepo.findAllByUser(username).stream()
                .filter(plant -> plant.getNonScName().toLowerCase().contains(searchedPlant.toLowerCase()))
                .toList();
    }

    public Plant deletePlant(String id, String username) {
        Optional<Plant> optionalPlant = plantRepo.findByIdAndUser(id, username);
        Plant plant = optionalPlant.orElseThrow(() -> new IllegalArgumentException("No plant with ID: " + id + " found!"));
        plantRepo.delete(plant);
        return plant;
    }

    public Optional<Plant> getPlantByIdAndUser(String id, String username) {
        return plantRepo.findByIdAndUser(id, username);
    }

    public Optional<Plant> changePlant(String id, Plant changedPlant) {
        return plantRepo.findById(id)
                .map(cP -> cP.updatePlant(changedPlant))
                .map(plantRepo :: save);
    }
    }


