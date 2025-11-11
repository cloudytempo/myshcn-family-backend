package org.example.service.impl;

import org.example.dto.RelationshipRequest;
import org.example.dto.RelationshipResponse;
import org.example.entity.Relationship;
import org.example.repository.RelationshipRepository;
import org.example.service.RelationshipService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationshipServiceImpl implements RelationshipService {

    private final RelationshipRepository repository;

    public RelationshipServiceImpl(RelationshipRepository repository) {
        this.repository = repository;
    }

    @Override
    public RelationshipResponse create(RelationshipRequest request) {
        Relationship r = Relationship.builder()
                .person1Id(request.getPerson1Id())
                .person2Id(request.getPerson2Id())
                .type(request.getType())
                .startDateYear(request.getStartDateYear())
                .endDateYear(request.getEndDateYear())
                .build();
        Relationship saved = repository.save(r);
        return toResponse(saved);
    }

    @Override
    public List<RelationshipResponse> listAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public RelationshipResponse findById(Long id) {
        return repository.findById(id).map(this::toResponse).orElseThrow(() -> new RuntimeException("Relationship not found"));
    }

    @Override
    public RelationshipResponse update(Long id, RelationshipRequest request) {
        Relationship r = repository.findById(id).orElseThrow(() -> new RuntimeException("Relationship not found"));
        r.setPerson1Id(request.getPerson1Id());
        r.setPerson2Id(request.getPerson2Id());
        r.setType(request.getType());
        r.setStartDateYear(request.getStartDateYear());
        r.setEndDateYear(request.getEndDateYear());
        Relationship updated = repository.save(r);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private RelationshipResponse toResponse(Relationship r) {
        return RelationshipResponse.builder()
                .id(r.getId())
                .person1Id(r.getPerson1Id())
                .person2Id(r.getPerson2Id())
                .type(r.getType() != null ? r.getType().name() : null)
                .startDateYear(r.getStartDateYear())
                .endDateYear(r.getEndDateYear())
                .createdAt(r.getCreatedAt())
                .build();
    }
}

