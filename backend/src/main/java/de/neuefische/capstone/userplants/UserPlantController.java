package de.neuefische.capstone.userplants;

import de.neuefische.capstone.plantcomponents.Plant;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
@AllArgsConstructor
@CrossOrigin
public class UserPlantController {

    private UserPlantService userPlantService;

    @GetMapping
    public Collection<Plant> getAllPlants(Principal principal){
        return userPlantService.getAllPlants(principal);
    }

    @GetMapping("/{scientificName}")
    public Plant getPlantByScName(@PathVariable String scientificName, Principal principal){
        return userPlantService.getPlantByScName(scientificName, principal);
    }

    @GetMapping("/{nonScientificName}")
    public Plant getPlantByNonScName(@PathVariable String nonScientificName, Principal principal){
        return userPlantService.getPlantByNonScName(nonScientificName, principal);
    }

    @GetMapping("/{location}")
    public Plant getPlantByLocation(@PathVariable String location, Principal principal){
        return userPlantService.getPlantByLocation(location, principal);
    }

}
