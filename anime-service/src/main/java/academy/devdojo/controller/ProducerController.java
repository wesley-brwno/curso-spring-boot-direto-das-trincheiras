package academy.devdojo.controller;

import academy.devdojo.domain.Anime;
import academy.devdojo.domain.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Producer> save(@RequestBody Producer producer, @RequestHeader HttpHeaders headers) {
        log.info("{}", headers);
        producer.setId(ThreadLocalRandom.current().nextLong(1, 1000));
        Producer.hardCoded().add(producer);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Authorization", "My key xpto");
//        return ResponseEntity.ok(producer);
        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).body(producer);
    }
}
