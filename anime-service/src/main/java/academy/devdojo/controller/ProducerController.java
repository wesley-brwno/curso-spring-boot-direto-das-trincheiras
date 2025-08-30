package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.response.ProducerGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RestController
@RequestMapping("v1/producers")
public class ProducerController {

    @GetMapping()
    public List<Producer> listAllParam(@RequestParam(required = false) String name) {
        if (Objects.isNull(name))
            return Producer.hardCoded();

        return Producer.hardCoded()
                .stream()
                .filter(producer -> producer.getName().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("{id}")
    public Producer findById(@PathVariable Long id) {
        return Producer.hardCoded()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key=1234")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {
        log.info("{}", headers);
        Producer producer = Producer.builder()
                .id(ThreadLocalRandom.current().nextLong(1, 1000))
                .name(producerPostRequest.getName())
                .createdAt(LocalDateTime.now())
                .build();

        Producer.hardCoded().add(producer);

        var response = ProducerGetResponse.builder()
                .id(producer.getId())
                .name(producer.getName())
                .createdAt(producer.getCreatedAt())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
