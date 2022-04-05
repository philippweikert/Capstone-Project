package de.neuefische.capstone.plantcomponents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantRepo plantRepo;

    public Plant createPlant (Plant plant, Principal principal){
        plant.setUser(principal.getName());
        return plantRepo.save(plant);
    }

    public List<Plant> getAllPlantsByUser(Principal principal) {
        return plantRepo.findAllByUser(principal.getName());
    }
}
