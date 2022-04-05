package de.neuefische.capstone.userplants;

import de.neuefische.capstone.plantcomponents.Plant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
@CrossOrigin
public class UserPlantController {

    private UserPlantService userPlantService;

    @GetMapping
    public Collection<Plant> getAllPlants(Principal principal){
        return userPlantService.getAllPlants(principal);
    }

    @GetMapping("/{scientificName}")
    public ResponseEntity<Plant>getPlant(@PathVariable String scientificName){
        return ResponseEntity.of(userPlantService.getPlant(scientificName));
    }

    @GetMapping("/{nonScientificName}")
    public ResponseEntity<Plant>getPlants(@PathVariable String nonScientificName){
        return ResponseEntity.of(userPlantService.getPlants(nonScientificName));
    }

    @GetMapping("/{location}")
    public ResponseEntity<Plant>getMorePlants(@PathVariable String location){
        return ResponseEntity.of(userPlantService.getMorePlants(location));
    }
}
