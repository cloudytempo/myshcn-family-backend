package org.example.service;

import org.example.dto.PersonRequest;
import org.example.dto.PersonResponse;

import java.util.List;

public interface PersonService {
    PersonResponse create(PersonRequest request);
    List<PersonResponse> listAll();
    PersonResponse findById(Long id);
    PersonResponse update(Long id, PersonRequest request);
    void delete(Long id);
}

