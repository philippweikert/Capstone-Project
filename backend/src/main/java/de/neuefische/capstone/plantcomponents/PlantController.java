package de.neuefische.capstone.plantcomponents;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plants/admin")
@RequiredArgsConstructor
@CrossOrigin
public class PlantController {

    private final PlantService plantService;

    @PostMapping
    public Plant createNewPlant (@RequestBody Plant plant){
        return plantService.createPlant(plant);
    }
}