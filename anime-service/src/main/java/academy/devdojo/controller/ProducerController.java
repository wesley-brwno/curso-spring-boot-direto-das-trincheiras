package academy.devdojo.controller;

import academy.devdojo.domain.Producer;
import academy.devdojo.mapper.ProducerMapper;
import academy.devdojo.request.ProducerPostRequest;
import academy.devdojo.request.ProducerPutRequest;
import academy.devdojo.response.ProducerGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("v1/producers")
public class ProducerController {

    private static final ProducerMapper MAPPER = ProducerMapper.INSTANCE;

    @GetMapping()
    public ResponseEntity<List<ProducerGetResponse>> listAllParam(@RequestParam(required = false) String name) {
        if (Objects.isNull(name))
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Producer.hardCoded()
                            .stream()
                            .map(MAPPER::toProducerGetResponse)
                            .toList());

        return ResponseEntity.status(HttpStatus.OK)
                .body(Producer.hardCoded()
                        .stream()
                        .filter(producer -> producer.getName().equalsIgnoreCase(name))
                        .map(MAPPER::toProducerGetResponse)
                        .toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProducerGetResponse> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(Producer.hardCoded()
                        .stream()
                        .filter(producer -> producer.getId().equals(id))
                        .findFirst()
                        .map(MAPPER::toProducerGetResponse)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found")));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers = "x-api-key=1234")
    public ResponseEntity<ProducerGetResponse> save(@RequestBody ProducerPostRequest producerPostRequest, @RequestHeader HttpHeaders headers) {
        log.info("{}", headers);

        var producer = MAPPER.toProducer(producerPostRequest);

        Producer.hardCoded().add(producer);

        var response = MAPPER.toProducerGetResponse(producer);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.debug("Request to delete producer by id: {}", id);
        Producer producerToDelete = Producer.hardCoded()
                .stream()
                .filter(producer -> producer.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));

        Producer.hardCoded().remove(producerToDelete);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<ProducerGetResponse> update(@RequestBody ProducerPutRequest request) {
        Producer producerToRemove = Producer.hardCoded()
                .stream()
                .filter(p -> p.getId().equals(request.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producer not Found"));

        Producer producerUpdated = MAPPER.toProducer(request, producerToRemove.getCreatedAt());
        Producer.hardCoded().remove(producerToRemove);
        Producer.hardCoded().add(producerUpdated);

        return ResponseEntity.status(HttpStatus.OK).body(MAPPER.toProducerGetResponse(producerUpdated));
    }
}
