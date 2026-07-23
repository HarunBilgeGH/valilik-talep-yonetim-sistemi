package com.staj.talepyonetimi.repository;

import java.util.List;
import java.util.Optional;

import com.staj.talepyonetimi.model.WorkRequest;

public interface RequestRepository {
    WorkRequest save(WorkRequest request);

    Optional<WorkRequest> findById(String id);

    Optional<WorkRequest> findByRequestNumber(String requestNumber);

    List<WorkRequest> findAll();

    boolean existsActiveRequestByTitleAndCreatedBy(String title, String createdByUserId); 
}
