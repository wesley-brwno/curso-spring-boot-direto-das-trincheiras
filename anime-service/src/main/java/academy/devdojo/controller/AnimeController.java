package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.mapper.AnimeMapper;
import academy.devdojo.request.AnimePostRequest;
import academy.devdojo.request.AnimePutRequest;
import academy.devdojo.response.AnimeGetResponse;
import academy.devdojo.service.AnimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("v1/animes")
@RequiredArgsConstructor
public class AnimeController {

    private final AnimeMapper mapper;
    private final AnimeService service;


    @GetMapping()
    public ResponseEntity<List<AnimeGetResponse>> listAllParam(@RequestParam(required = false) String name) {
        log.debug("Request received to list all animes, param name {}", name);

        List<Anime> animeList = service.findAll(name);

        return ResponseEntity.ok(mapper.toAnimeGetResponseList(animeList));
    }

    @GetMapping("{id}")
    public ResponseEntity<AnimeGetResponse> findById(@PathVariable Long id) {
        log.debug("Request received to find anime by Id {}", id);

        Anime anime = service.findByIdOrThrowNotFoundException(id);

        AnimeGetResponse animeGetResponse = mapper.toAnimeGetResponse(anime);

        return ResponseEntity.ok(animeGetResponse);
    }

    @PostMapping
    public ResponseEntity<AnimeGetResponse> save(@RequestBody AnimePostRequest animePostRequest) {
        log.debug("Request to save anime {}", animePostRequest);

        Anime anime = mapper.toAnime(animePostRequest);

        Anime animeSaved = service.save(anime);

        AnimeGetResponse animeGetResponse = mapper.toAnimeGetResponse(animeSaved);

        return ResponseEntity.ok(animeGetResponse);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete anime by id: {}", id);

        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody AnimePutRequest request) {
        log.debug("Request to update an Anime {}", request);

        Anime anime = mapper.toAnime(request);

        service.update(anime);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
