package academy.devdojo.SpringBoot2.util;

import academy.devdojo.SpringBoot2.domain.Anime;
import academy.devdojo.SpringBoot2.requests.AnimePostRequestBody;

public class AnimePostRequestBodyCreator {

    public static AnimePostRequestBody createAnimePostRequestBody(){
        return AnimePostRequestBody.builder()
                .name(AnimeCreator.createValidAnime().getName())
                .build();
    }

}
