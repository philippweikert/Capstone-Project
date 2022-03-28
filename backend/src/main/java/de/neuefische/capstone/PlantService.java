package de.neuefische.capstone;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlantService {

    private final PlantRepo plantRepo;

    public Plant createPlant (Plant plant){
        return plantRepo.save(plant);
    }
}
