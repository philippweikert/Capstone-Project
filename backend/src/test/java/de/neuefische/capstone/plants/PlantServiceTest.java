package de.neuefische.capstone.plants;

import de.neuefische.capstone.plantcomponents.Plant;
import de.neuefische.capstone.plantcomponents.PlantRepo;
import de.neuefische.capstone.plantcomponents.PlantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Principal;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlantServiceTest {

    Principal testPrincipal = () -> "TestUser";

    @Test
    void shouldAddNewPlant(){
        Plant plant = new Plant(null, "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");

        PlantRepo plantRepo = mock(PlantRepo.class);
        when(plantRepo.save(plant)).thenReturn(plant);

        PlantService plantService = new PlantService(plantRepo);
        Plant actual = plantService.createPlant(plant, testPrincipal);

        assertEquals(plant, actual);

    }

    @Test
    void shouldReturnListOfPlants(){

        Plant testPlant1 = new Plant("1898","Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        Plant testPlant2 = new Plant("0815", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        List<Plant> plantList = List.of(testPlant1, testPlant2);
        PlantRepo plantRepoMock = Mockito.mock(PlantRepo.class);
        when(plantRepoMock.findAllByUser("TestUser")).thenReturn(plantList);
        PlantService plantService = new PlantService(plantRepoMock);

        List<Plant>actual = plantService.getAllPlantsByUser(testPrincipal);

        assertEquals(plantList,actual);
    }

}