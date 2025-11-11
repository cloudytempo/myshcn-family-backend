package org.example.controller;

import org.example.dto.ParentChildRequest;
import org.example.dto.ParentChildResponse;
import org.example.service.ParentChildService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parent-child")
public class ParentChildController {

    private final ParentChildService service;

    public ParentChildController(ParentChildService service) {
        this.service = service;
    }

    @GetMapping
    public List<ParentChildResponse> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ParentChildResponse get(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ParentChildResponse create(@Validated @RequestBody ParentChildRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}")
    public ParentChildResponse update(@PathVariable Long id, @Validated @RequestBody ParentChildRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

