package academy.devdojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Producer {

    private Long id;
    @JsonProperty("name")
    private String name;
    private static List<Producer> producers = new ArrayList<>();

    static {
        List<Producer> list = new ArrayList<>();

        list.add(Producer.builder()
                .id(1L)
                .name("Mappa")
                .build());

        list.add(Producer.builder()
                .id(2L)
                .name("Kyoto Animatio")
                .build());

        list.add(Producer.builder()
                .id(3L)
                .name("UFO Table")
                .build());

        list.add(Producer.builder()
                .id(4L)
                .name("Mad House")
                .build());

        list.add(Producer.builder()
                .id(5L)
                .name("Toy Animation")
                .build());

        producers = list;
    }


    public static List<Producer> hardCoded() {
        return producers;
    }
}
