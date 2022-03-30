package de.neuefische.capstone.plantcomponents;

import de.neuefische.capstone.plantcomponents.Plant;
import de.neuefische.capstone.plantcomponents.PlantRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final PlantRepo plantRepo;

    public Plant createPlant (Plant plant){
        return plantRepo.save(plant);
    }

}
