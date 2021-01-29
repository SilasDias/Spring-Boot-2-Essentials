package academy.devdojo.SpringBoot2.integration;

import academy.devdojo.SpringBoot2.domain.Anime;
import academy.devdojo.SpringBoot2.repository.AnimeRepository;
import academy.devdojo.SpringBoot2.util.AnimeCreator;
import academy.devdojo.SpringBoot2.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class AnimeControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private AnimeRepository animeRepository;
    // Anotação para pegar a porta usada no teste.
    /*@LocalServerPort
    private int port;*/

    @Test
    @DisplayName("list returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimesInsidePageObject_WhenSuccessful(){
        // Vamos criar um anime no banco de dados
        Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());

        String expectedName = savedAnime.getName();

        // Faz uma requisição para o controler
        PageableResponse<Anime> animePage = testRestTemplate.exchange("/animes", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Anime>>() {
                }).getBody();

        Assertions.assertThat(animePage).isNotNull();

        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

}
