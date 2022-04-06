package de.neuefische.capstone.plantcomponents;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/api/plants/admin")
@RequiredArgsConstructor
@CrossOrigin
public class PlantController {

    private final PlantService plantService;

    @PostMapping
    public Plant createNewPlant (@RequestBody Plant plant, Principal principal){
        return plantService.createPlant(plant, principal);
    }

    @GetMapping
    public Collection<Plant> getAllPlants(Principal principal){
        return plantService.getAllPlantsByUser(principal);
    }
}
