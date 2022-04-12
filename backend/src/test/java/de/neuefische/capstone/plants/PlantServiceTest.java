package de.neuefische.capstone.plants;

import de.neuefische.capstone.plantcomponents.Plant;
import de.neuefische.capstone.plantcomponents.PlantRepo;
import de.neuefische.capstone.plantcomponents.PlantService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;


import static com.mongodb.assertions.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PlantServiceTest {

    @Test
    void shouldAddNewPlant(){
        Plant plant = new Plant(null, "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");

        PlantRepo plantRepo = mock(PlantRepo.class);
        when(plantRepo.save(plant)).thenReturn(plant);

        PlantService plantService = new PlantService(plantRepo);
        Plant actual = plantService.createPlant(plant, "Hans Hansen");

        assertEquals(plant, actual);

    }

    @Test
    void shouldReturnListOfPlants(){

        Plant testPlant1 = new Plant("1898","Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        Plant testPlant2 = new Plant("0815", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        List<Plant> plantList = List.of(testPlant1, testPlant2);
        PlantRepo plantRepoMock = Mockito.mock(PlantRepo.class);
        when(plantRepoMock.findAllByUser("Hans Hansen")).thenReturn(plantList);
        PlantService plantService = new PlantService(plantRepoMock);

        List<Plant>actual = plantService.getAllPlantsByUser("Hans Hansen");

        assertEquals(plantList,actual);
    }

    @Test
    void shouldFindCertainPlant(){

        Plant testPlant1 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        Plant testPlant2 = new Plant("0815", "Ficus elastica", "Gänseblümchen", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        PlantRepo testRepo = Mockito.mock(PlantRepo.class);
        String username = "Hans Hansen";
        PlantService plantService = new PlantService(testRepo);

        when(testRepo.findAllByUser(username)).thenReturn(List.of(testPlant1, testPlant2));

        assertEquals(List.of(testPlant1), plantService.getMatchingPlant("Gummibaum", username));

    }

    @Test
    void shouldFindOnePlantById(){

        Plant testPlant1 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        PlantRepo plantRepo = Mockito.mock(PlantRepo.class);
        PlantService plantService = new PlantService(plantRepo);
        String id = testPlant1.getId();

        when(plantRepo.findById(id)).thenReturn(Optional.of(testPlant1));

        Optional<Plant> actual = plantService.getPlantByIdAndUser(id, "Hans Hansen");

        assertEquals(actual, actual);
    }

    @Test
    void shouldDeletePlantById(){

        Plant testPlant1 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        String id = testPlant1.getId();
        PlantRepo plantRepo = Mockito.mock(PlantRepo.class);
        PlantService plantService = new PlantService(plantRepo);

        when(plantRepo.findByIdAndUser(id, "Hans Hansen")).thenReturn(Optional.of(testPlant1));

        Plant actual = plantService.deletePlant(testPlant1.getId(), "Hans Hansen");

        verify(plantRepo).delete(testPlant1);
        assertEquals(testPlant1,actual);
    }

    @Test
    void shouldThrowWithUnknownIDError(){

        PlantRepo plantRepo = Mockito.mock(PlantRepo.class);
        PlantService plantService = new PlantService(plantRepo);
        when(plantRepo.findByIdAndUser("N/A", "Hans Hansen")).thenReturn(Optional.empty());

        try{
            plantService.deletePlant("N/A", "Hans Hansen");
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("No plant with ID: N/A found!", e.getMessage());
        }
    }


}