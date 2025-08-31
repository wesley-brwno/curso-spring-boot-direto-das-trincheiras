package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producer {

    @EqualsAndHashCode.Include
    private Long id;
    @JsonProperty("name")
    private String name;
    private LocalDateTime createdAt;
    private static List<Producer> producers = new ArrayList<>();

    static {
        List<Producer> list = new ArrayList<>();

        list.add(Producer.builder()
                .id(1L)
                .name("Mappa")
                .createdAt(LocalDateTime.now())
                .build());

        list.add(Producer.builder()
                .id(2L)
                .name("Kyoto Animatio")
                .createdAt(LocalDateTime.now())
                .build());

        list.add(Producer.builder()
                .id(3L)
                .name("UFO Table")
                .createdAt(LocalDateTime.now())
                .build());

        list.add(Producer.builder()
                .id(4L)
                .name("Mad House")
                .createdAt(LocalDateTime.now())
                .build());

        list.add(Producer.builder()
                .id(5L)
                .name("Toy Animation")
                .createdAt(LocalDateTime.now())
                .build());

        producers = list;
    }


    public static List<Producer> hardCoded() {
        return producers;
    }
}
