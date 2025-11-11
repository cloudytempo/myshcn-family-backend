package org.example.controller;

import org.example.dto.PersonRequest;
import org.example.dto.PersonResponse;
import org.example.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<PersonResponse> list() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonResponse get(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public PersonResponse create(@Validated @ModelAttribute PersonRequest request) {
        return personService.create(request);
    }

    @PutMapping(path = "/{id}", consumes = {"multipart/form-data"})
    public PersonResponse update(@PathVariable Long id, @Validated @ModelAttribute PersonRequest request) {
        return personService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

