package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.response.AnimeGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("v1/animes")
public class AnimeController {

    private static final AnimeMapper MAPPER = AnimeMapper.INSTANCE;

    @GetMapping()
    public ResponseEntity<List<AnimeGetResponse>> listAllParam(@RequestParam(required = false) String name) {
        if (Objects.isNull(name)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Anime.hardCoded().stream().map(MAPPER::toAnimeGetResponse).toList());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(Anime.hardCoded()
                        .stream()
                        .filter(anime -> anime.getName().equalsIgnoreCase(name))
                        .map(MAPPER::toAnimeGetResponse)
                        .toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        Anime anime = Anime.hardCoded()
                .stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);
        return ResponseEntity.status(HttpStatus.OK).body(MAPPER.toAnimeGetResponse(anime));
    }

    @PostMapping
    public ResponseEntity<AnimeGetResponse> save(@RequestBody AnimePostRequest animePostRequest) {
        Anime anime = MAPPER.toAnime(animePostRequest);
        Anime.hardCoded().add(anime);
        return ResponseEntity.status(HttpStatus.CREATED).body(MAPPER.toAnimeGetResponse(anime));
    }
}
