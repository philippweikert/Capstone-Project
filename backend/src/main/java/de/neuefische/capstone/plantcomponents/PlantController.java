package de.neuefische.capstone.plantcomponents;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/plants/admin")
@RequiredArgsConstructor
@CrossOrigin
public class PlantController {

    private final PlantService plantService;

    @PostMapping
    public Plant createNewPlant(@RequestBody Plant plant, Principal principal) {
        return plantService.createPlant(plant, principal.getName());
    }

    @GetMapping
    public Collection<Plant> getAllPlants(Principal principal) {
        return plantService.getAllPlantsByUser(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Plant> getPlantById(@PathVariable String id, Principal principal){
        return ResponseEntity.of(plantService.getPlantByIdAndUser(id, principal.getName()));
    }

    @GetMapping("/search/{searchedPlant}")
    public List<Plant> getMatchingPlants(@PathVariable String searchedPlant, Principal principal) {
        return plantService.getMatchingPlant(searchedPlant, principal.getName());
    }

    @DeleteMapping("/{id}")
    public Plant deletePlantById (@PathVariable String id, Principal principal){
        return plantService.deletePlant(id, principal.getName());
    }

    @PutMapping("/{id}")
    public Collection<Plant> changePlant (@PathVariable String id, @RequestBody Plant changedPlant, Principal principal){
        plantService.changePlant(id, changedPlant);
        return plantService.getAllPlantsByUser(principal.getName());
    }

}
