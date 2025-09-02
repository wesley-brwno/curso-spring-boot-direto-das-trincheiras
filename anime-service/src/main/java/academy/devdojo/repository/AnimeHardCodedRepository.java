package academy.devdojo.repository;

import academy.devdojo.domain.Anime;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnimeHardCodedRepository {
    private static List<Anime> animeList = new ArrayList<>();

    static {
        List<Anime> list = new ArrayList<>();

        list.add(Anime.builder()
                .id(1L)
                .name("Naruto")
                .build());

        list.add(Anime.builder()
                .id(2L)
                .name("One Piece")
                .build());

        list.add(Anime.builder()
                .id(3L)
                .name("Sakura Quest")
                .build());

        list.add(Anime.builder()
                .id(4L)
                .name("Full Metal Alchemist Brotherhood")
                .build());

        list.add(Anime.builder()
                .id(5L)
                .name("Digimon")
                .build());

        animeList = list;
    }


    public List<Anime> findAll() {
        return animeList;
    }

    public Optional<Anime> findById(Long id) {
        return animeList.stream().filter(anime -> anime.getId().equals(id)).findFirst();
    }

    public List<Anime> findByName(String name) {
        return animeList.stream().filter(anime -> anime.getName().equalsIgnoreCase(name)).toList();
    }

    public Anime save(Anime anime) {
        animeList.add(anime);
        return anime;
    }

    public void delete(Anime anime) {
        animeList.remove(anime);
    }

    public void update(Anime anime) {
        delete(anime);
        save(anime);
    }
}
