package academy.devdojo.service;

import academy.devdojo.domain.Anime;
import academy.devdojo.repository.AnimeHardCodedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnimeService {
    private final AnimeHardCodedRepository repository;

    public List<Anime> findAll(String name) {
        return Objects.isNull(name) ? repository.findAll() : repository.findByName(name);
    }

    public Anime findByIdOrThrowNotFoundException(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anime not Found"));
    }

    public Anime save(Anime anime) {
        return repository.save(anime);
    }

    public void delete(Long id) {
        Anime anime = findByIdOrThrowNotFoundException(id);
        repository.delete(anime);
    }

    public void update(Anime animeToUpdate) {

        assertAnimeExists(animeToUpdate.getId());

        repository.update(animeToUpdate);
    }

    private void assertAnimeExists(Long id) {
        findByIdOrThrowNotFoundException(id);
    }
}
