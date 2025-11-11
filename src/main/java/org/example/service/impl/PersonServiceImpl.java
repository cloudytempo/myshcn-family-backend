package org.example.service.impl;

import org.example.dto.PersonRequest;
import org.example.dto.PersonResponse;
import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.example.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final CloudinaryService cloudinaryService;

    public PersonServiceImpl(PersonRepository repository, CloudinaryService cloudinaryService) {
        this.repository = repository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public PersonResponse create(PersonRequest request) {
        Person p = Person.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .dateOfDeath(request.getDateOfDeath())
                .notes(request.getNotes())
                .build();

        MultipartFile photo = request.getPhoto();
        if (photo != null && !photo.isEmpty()) {
            String url = cloudinaryService.upload(photo);
            p.setPhotoUrl(url);
        }

        Person saved = repository.save(p);
        return toResponse(saved);
    }

    @Override
    public List<PersonResponse> listAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public PersonResponse findById(Long id) {
        return repository.findById(id).map(this::toResponse).orElseThrow(() -> new RuntimeException("Person not found"));
    }

    @Override
    public PersonResponse update(Long id, PersonRequest request) {
        Person p = repository.findById(id).orElseThrow(() -> new RuntimeException("Person not found"));
        p.setFirstName(request.getFirstName());
        p.setLastName(request.getLastName());
        p.setGender(request.getGender());
        p.setDateOfBirth(request.getDateOfBirth());
        p.setDateOfDeath(request.getDateOfDeath());
        p.setNotes(request.getNotes());
        MultipartFile photo = request.getPhoto();
        if (photo != null && !photo.isEmpty()) {
            String url = cloudinaryService.upload(photo);
            p.setPhotoUrl(url);
        }
        Person updated = repository.save(p);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PersonResponse toResponse(Person p) {
        return PersonResponse.builder()
                .id(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .gender(p.getGender() != null ? p.getGender().name() : null)
                .dateOfBirth(p.getDateOfBirth())
                .dateOfDeath(p.getDateOfDeath())
                .notes(p.getNotes())
                .photoUrl(p.getPhotoUrl())
                .createdAt(p.getCreatedAt())
                .build();
    }
}

