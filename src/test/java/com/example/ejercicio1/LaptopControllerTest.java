package com.example.ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+ port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

    }

    @Test
    void buscarLaptops() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/laptops", Laptop[].class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());


        List<Laptop> books = Arrays.asList(response.getBody());
        System.out.println(books.size());
    }

    @Test
    void bucarPorId() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/laptops/1", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "marca": "Bangho",
                        "modelo": "Futura",
                        "memoria": 900,
                        "procesador": "Intel",
                        "precio": 500.99
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops",HttpMethod.POST, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Bangho", result.getMarca());
    }

    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                     "id"= : 1
                    "marca": "Bangho",
                        "modelo": "Futura",
                        "memoria": 900,
                        "procesador": "Intel",
                        "precio": 500.99
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/laptops",HttpMethod.PUT, request, Laptop.class);

        Laptop result = response.getBody();

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }


        @Test
    void delete() {
            ResponseEntity<Void> deleteResponse = testRestTemplate.exchange("/laptops/1", HttpMethod.DELETE, null, Void.class);
            assertEquals(HttpStatus.NO_CONTENT ,deleteResponse.getStatusCode() );

    }

    @Test
    void deleteAll() {
        ResponseEntity<Void> deleteResponse = testRestTemplate.exchange("/laptops", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NO_CONTENT ,deleteResponse.getStatusCode() );
    }
}