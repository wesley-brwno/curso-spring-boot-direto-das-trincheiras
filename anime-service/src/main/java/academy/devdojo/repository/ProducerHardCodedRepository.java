package academy.devdojo.repository;

import academy.devdojo.domain.Producer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProducerHardCodedRepository {

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


    public List<Producer> findAll() {
        return producers;
    }

    public Optional<Producer> findById(Long id) {
       return producers.stream().filter(producer -> producer.getId().equals(id)).findFirst();
    }

    public List<Producer> findByName(String name) {
        return producers.stream().filter(producer -> producer.getName().equalsIgnoreCase(name)).toList();
    }

    public Producer save(Producer producer) {
        producers.add(producer);
        return producer;
    }

    public void delete(Producer producer) {
        producers.remove(producer);
    }

    public void update(Producer producer) {
        delete(producer);
        save(producer);
    }
}
