package de.neuefische.capstone;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
@CrossOrigin
public class PlantController {

    private final PlantService plantService;

    @PostMapping
    public Plant createPlant (@RequestBody Plant plant){
        return plantService.createPlant(plant);
    }
}
