package de.neuefische.capstone.plants;

import de.neuefische.capstone.plantcomponents.Plant;
import de.neuefische.capstone.user.create.AppUser;
import de.neuefische.capstone.user.login.CreateUserCredentials;
import de.neuefische.capstone.user.login.Credentials;
import de.neuefische.capstone.user.login.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlantControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

   @Test
    void integrationTest() {
        CreateUserCredentials userCredentials = new CreateUserCredentials("Hans Hansen", "aaa", "aaa");
        restTemplate.postForEntity("/api/createuser", userCredentials, AppUser.class);
        Credentials credentials = new Credentials("Hans Hansen", "aaa");
        ResponseEntity<Token> response1 = restTemplate.postForEntity("/api/login", credentials, Token.class);
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        String token = response1.getBody().getToken();
        Plant plant1 =new Plant(null, "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");

        ResponseEntity<Plant> actualResponse = restTemplate.exchange("/api/plants/admin",
                HttpMethod.POST,
                new HttpEntity<>(plant1, createHeaders(token)),
                Plant.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        Plant actual = actualResponse.getBody();
        assertEquals(plant1.getScientificName(), actual.getScientificName());
    }

    @Test
    void shouldGiveBackAllPlants(){
        CreateUserCredentials createUserCredentials = new CreateUserCredentials("Hans Hansen", "aaa", "aaa");
        restTemplate.postForEntity("/api/createuser", createUserCredentials, AppUser.class);
        Credentials credentials = new Credentials("Hans Hansen", "aaa");
        ResponseEntity<Token> tokenResponseEntity = restTemplate.postForEntity("/api/login", credentials, Token.class);
        String token = tokenResponseEntity.getBody().getToken();
        Plant plant1 = new Plant("0815", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        Plant plant2 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        restTemplate.exchange("/api/plants/admin", HttpMethod.POST,new HttpEntity<>(plant1,createHeaders(token)), Plant.class);
        restTemplate.exchange("/api/plants/admin", HttpMethod.POST,new HttpEntity<>(plant2,createHeaders(token)), Plant.class);

        ResponseEntity<Plant[]> actualResponse = restTemplate.exchange("/api/plants/admin",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                Plant[].class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    private HttpHeaders createHeaders(String token){
        String authHeaders = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeaders);

        return headers;
    }

    @Test
    void shouldDeletePlantById(){

        CreateUserCredentials createUserCredentials = new CreateUserCredentials("Hans Hansen", "aaa", "aaa");
        restTemplate.postForEntity("/api/createuser", createUserCredentials, AppUser.class);
        Plant plant1 = new Plant("0815", "Ficus elastica", "Gänseblümchen", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        String url = "/api/plants/admin/" + plant1.getId();
        Credentials credentials = new Credentials("Hans Hansen", "aaa");
        ResponseEntity<Token> tokenResponseEntity = restTemplate.postForEntity("/api/login", credentials, Token.class);
        String token = tokenResponseEntity.getBody().getToken();

        restTemplate.delete(url);
        ResponseEntity<Plant[]> actualResponse = restTemplate.exchange("/api/plants/admin",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                Plant[].class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        List<Plant> actual = Arrays.stream(Objects.requireNonNull(actualResponse.getBody())).toList();
        System.out.println("LÄNGE === " + actual.size());
        assertFalse(actual.contains(plant1));

    }

    @Test
    void shouldGetPlantByID(){

        CreateUserCredentials createUserCredentials = new CreateUserCredentials("Hans Hansen", "aaa", "aaa");
        restTemplate.postForEntity("/api/createuser", createUserCredentials, AppUser.class);
        Credentials credentials = new Credentials("Hans Hansen", "aaa");
        ResponseEntity<Token> tokenResponseEntity = restTemplate.postForEntity("/api/login", credentials, Token.class);
        String token = tokenResponseEntity.getBody().getToken();
        Plant plant1 = new Plant("0815", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        Plant plant2 = new Plant("1898", "Ficus elastica", "Gummibaum", "Schattig", "mäßig gießen", "ist vorhanden", "einmal im Jahr", "nicht notwendig", "Hans Hansen");
        restTemplate.exchange("/api/plants/admin", HttpMethod.POST,new HttpEntity<>(plant1,createHeaders(token)), Plant.class);
        restTemplate.exchange("/api/plants/admin", HttpMethod.POST,new HttpEntity<>(plant2,createHeaders(token)), Plant.class);

        ResponseEntity<Plant[]> allPlantsResponse = restTemplate.exchange("/api/plants/admin",
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                Plant[].class);
        String id = allPlantsResponse.getBody()[0].getId();
        Plant expected = allPlantsResponse.getBody()[0];
        String url = "/api/plants/admin/"+id;

        ResponseEntity<Plant> actualResponse = restTemplate.exchange(url,
                HttpMethod.GET,
                new HttpEntity<>(createHeaders(token)),
                Plant.class);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        Plant actual = actualResponse.getBody();
        assertEquals(expected, actual);

    }

}