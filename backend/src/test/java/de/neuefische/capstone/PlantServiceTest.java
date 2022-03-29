package de.neuefische.capstone;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlantServiceTest {

    @Test
    void shouldAddNewPlant(){
        Plant plant = new Plant(null, "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig");
        Plant savedPlant = new Plant("0815", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig");

        PlantRepo plantRepo = mock(PlantRepo.class);
        when(plantRepo.save(plant)).thenReturn(savedPlant);

        PlantService plantService = new PlantService(plantRepo);
        Plant actual = plantService.createPlant(plant);

        assertThat(actual).isSameAs(savedPlant);

    }

}