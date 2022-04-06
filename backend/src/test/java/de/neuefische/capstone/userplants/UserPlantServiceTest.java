package de.neuefische.capstone.userplants;

import de.neuefische.capstone.plantcomponents.Plant;
import de.neuefische.capstone.plantcomponents.PlantRepo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserPlantServiceTest {

    Principal testPrincipal = () -> "TestUser";

    @Test
    void shouldReturnListOfPlants() {

        Plant testPlant1 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        Plant testPlant2 = new Plant("0815", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        List<Plant> plantList = List.of(testPlant1, testPlant2);
        PlantRepo plantRepoMock = Mockito.mock(PlantRepo.class);
        when(plantRepoMock.findAllByUser("TestUser")).thenReturn(plantList);
        UserPlantService userPlantService = new UserPlantService(plantRepoMock);

        List<Plant> actual = userPlantService.getAllPlants(testPrincipal);

        assertEquals(plantList, actual);
    }

    @Test
    void shouldFindPlantByScName() {

        Plant testPlant1 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        String scName = testPlant1.getScientificName();
        PlantRepo plantRepo = Mockito.mock(PlantRepo.class);
        when(plantRepo.findByScientificName(scName, "TestUser")).thenReturn(Optional.of(testPlant1));
        UserPlantService userPlantService = new UserPlantService(plantRepo);

        Plant actual = userPlantService.getPlantByScName(scName, testPrincipal);

        assertEquals(testPlant1, actual);

    }

    @Test
    void shouldFindPlantByNonScName() {

        Plant testPlant1 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        String nonScName = testPlant1.getScientificName();
        PlantRepo plantRepo = Mockito.mock(PlantRepo.class);
        when(plantRepo.findByNonScName(nonScName, "TestUser")).thenReturn(Optional.of(testPlant1));
        UserPlantService userPlantService = new UserPlantService(plantRepo);

        Plant actual = userPlantService.getPlantByNonScName(nonScName, testPrincipal);

        assertEquals(testPlant1, actual);
    }

    @Test
    void shouldFindPlantByLocation() {

        Plant testPlant1 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        String location = testPlant1.getScientificName();
        PlantRepo plantRepo = Mockito.mock(PlantRepo.class);
        when(plantRepo.findByLocation(location, "TestUser")).thenReturn(Optional.of(testPlant1));
        UserPlantService userPlantService = new UserPlantService(plantRepo);

        Plant actual = userPlantService.getPlantByLocation(location, testPrincipal);

        assertEquals(testPlant1, actual);
    }

}

