package de.neuefische.capstone.userplants;

import de.neuefische.capstone.plantcomponents.Plant;
import de.neuefische.capstone.plantcomponents.PlantRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserPlantServiceTest {

    Principal testPrincipal = new Principal() {
        @Override
        public String getName() {
            return "TestUser";
        }
    };

    @Test
    void shouldReturnListOfPlants(){

        Plant testPlant1 = new Plant("1898","Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig");
        Plant testPlant2 = new Plant("0815", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig");
        List<Plant> plantList = List.of(testPlant1, testPlant2);
        PlantRepo plantRepoMock = Mockito.mock(PlantRepo.class);
        when(plantRepoMock.findAllByUser("TestUser")).thenReturn(plantList);
        UserPlantService userPlantService = new UserPlantService(plantRepoMock);

        List<Plant>actual = userPlantService.getAllPlants(testPrincipal);

        assertEquals(plantList,actual);
    }

}