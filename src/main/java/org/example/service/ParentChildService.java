package org.example.service;

import org.example.dto.ParentChildRequest;
import org.example.dto.ParentChildResponse;

import java.util.List;

public interface ParentChildService {
    ParentChildResponse create(ParentChildRequest request);
    List<ParentChildResponse> listAll();
    ParentChildResponse findById(Long id);
    ParentChildResponse update(Long id, ParentChildRequest request);
    void delete(Long id);
}

