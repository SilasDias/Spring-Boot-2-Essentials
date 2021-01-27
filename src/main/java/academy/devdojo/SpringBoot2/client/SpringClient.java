package academy.devdojo.SpringBoot2.client;

import academy.devdojo.SpringBoot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}", Anime.class,2);
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class,2);

        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);

        log.info(Arrays.toString(animes));

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Anime>>() {
                });

        log.info(exchange.getBody());

        /*Anime kingdom = Anime.builder().name("kingdom").build();
        Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes/", kingdom, Anime.class);
        log.info("saved anime {}", kingdomSaved);*/

        /*Anime kingdom = Anime.builder().name("kingdom").build();
        ResponseEntity<Anime> kingdomSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST, new HttpEntity<>(kingdom), Anime.class);
        log.info("saved anime {}", kingdomSaved);*/

        Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(samuraiChamploo, createJsonHeader()),
                Anime.class);

        log.info("saved anime {}", samuraiChamplooSaved);

    }

    public static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
