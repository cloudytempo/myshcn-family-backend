package org.example.service;

import org.example.dto.RelationshipRequest;
import org.example.dto.RelationshipResponse;

import java.util.List;

public interface RelationshipService {
    RelationshipResponse create(RelationshipRequest request);
    List<RelationshipResponse> listAll();
    RelationshipResponse findById(Long id);
    RelationshipResponse update(Long id, RelationshipRequest request);
    void delete(Long id);
}

