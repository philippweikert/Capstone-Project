package de.neuefische.capstone.plants;

import de.neuefische.capstone.plantcomponents.Plant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlantControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void integrationTest() {
        Plant plant1 = new Plant("0815", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig");

        ResponseEntity<Plant> response1 = restTemplate.postForEntity("/api/plants", plant1, Plant.class);

        assertThat(response1.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response1.getBody()).isEqualTo(plant1);

    }

}