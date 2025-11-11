package org.example.service.impl;

import org.example.dto.ParentChildRequest;
import org.example.dto.ParentChildResponse;
import org.example.entity.ParentChild;
import org.example.repository.ParentChildRepository;
import org.example.service.ParentChildService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentChildServiceImpl implements ParentChildService {

    private final ParentChildRepository repository;

    public ParentChildServiceImpl(ParentChildRepository repository) {
        this.repository = repository;
    }

    @Override
    public ParentChildResponse create(ParentChildRequest request) {
        ParentChild pc = ParentChild.builder()
                .parentId(request.getParentId())
                .childId(request.getChildId())
                .type(request.getType())
                .build();
        ParentChild saved = repository.save(pc);
        return toResponse(saved);
    }

    @Override
    public List<ParentChildResponse> listAll() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public ParentChildResponse findById(Long id) {
        return repository.findById(id).map(this::toResponse).orElseThrow(() -> new RuntimeException("ParentChild not found"));
    }

    @Override
    public ParentChildResponse update(Long id, ParentChildRequest request) {
        ParentChild pc = repository.findById(id).orElseThrow(() -> new RuntimeException("ParentChild not found"));
        pc.setParentId(request.getParentId());
        pc.setChildId(request.getChildId());
        pc.setType(request.getType());
        ParentChild updated = repository.save(pc);
        return toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ParentChildResponse toResponse(ParentChild pc) {
        return ParentChildResponse.builder()
                .id(pc.getId())
                .parentId(pc.getParentId())
                .childId(pc.getChildId())
                .type(pc.getType() != null ? pc.getType().name() : null)
                .createdAt(pc.getCreatedAt())
                .build();
    }
}

