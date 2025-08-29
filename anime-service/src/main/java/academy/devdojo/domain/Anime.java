package academy.devdojo.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Anime {

    private Long id;
    private String name;
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


    public static List<Anime> hardCoded() {
        return animeList;
    }
}
