package com.staj.talepyonetimi.repository.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.staj.talepyonetimi.model.WorkRequest;
import com.staj.talepyonetimi.repository.RequestRepository;

public class InMemoryRequestRepository implements RequestRepository {
    private final List<WorkRequest> requests = new ArrayList<>();

    

    @Override
    public Optional<WorkRequest> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        for (WorkRequest request : requests) {
            if (request.getId() != null && request.getId().equals(id)) {
                return Optional.of(request);
            }
        }
        return Optional.empty();
    }

    @Override
    public WorkRequest save(WorkRequest request) {
        if (request == null) {
            return null;
        }
        if (request.getId() == null) {
            this.requests.add(request);
            return request;
        }
        for (int i = 0;i<requests.size();i++) {
            WorkRequest existingRequest = requests.get(i);
            if (existingRequest.getId() != null && existingRequest.getId().equals(request.getId())) {
                requests.set(i, request);
                return request;
            }
        }
        this.requests.add(request);
        return request;
    }

    @Override
    public Optional<WorkRequest> findByRequestNumber(String requestNumber) {
        if (requestNumber == null) {
            return Optional.empty();
        }
        for (WorkRequest request : requests) {
            if (request.getRequestNumber() != null && request.getRequestNumber().equals(requestNumber)) {
                return Optional.of(request);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<WorkRequest> findAll() {
        return List.copyOf(requests);
    }

    @Override
    public boolean existsActiveRequestByTitleAndCreatedBy(String title, Long createdByUserId) {
        if (title == null || createdByUserId == null) {
            return false;
        }

        for (WorkRequest request : requests) {
            if (request.getTitle() != null
                    && request.getTitle().equalsIgnoreCase(title)
                    && request.getCreatedBy() != null
                    && request.getCreatedBy().getId() != null
                    && request.getCreatedBy().getId().equals(createdByUserId)
                    && !request.isTerminal()) {
                return true;
            }
        }

        return false;
    }
    
}
