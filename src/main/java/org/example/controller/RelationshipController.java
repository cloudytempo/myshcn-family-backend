package org.example.controller;

import org.example.dto.RelationshipRequest;
import org.example.dto.RelationshipResponse;
import org.example.service.RelationshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationships")
public class RelationshipController {

    private final RelationshipService service;

    public RelationshipController(RelationshipService service) {
        this.service = service;
    }

    @GetMapping
    public List<RelationshipResponse> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public RelationshipResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public RelationshipResponse create(@Validated @RequestBody RelationshipRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public RelationshipResponse update(@PathVariable Long id, @Validated @RequestBody RelationshipRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

