package com.staj.talepyonetimi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.staj.talepyonetimi.enums.RequestCategory;
import com.staj.talepyonetimi.enums.RequestPriority;
import com.staj.talepyonetimi.enums.RequestStatus;
import com.staj.talepyonetimi.model.User;
import com.staj.talepyonetimi.model.WorkRequest;
import com.staj.talepyonetimi.repository.RequestRepository;
import com.staj.talepyonetimi.util.IdGenerator;
import com.staj.talepyonetimi.util.RequestNumberGenerator;

public class RequestService {

    private final RequestRepository requestRepository;
    private final IdGenerator commentIdGenerator;
    private final RequestNumberGenerator requestNumberGenerator;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
        this.commentIdGenerator = new IdGenerator("C");
        this.requestNumberGenerator = new RequestNumberGenerator("R");
    }
    public List<WorkRequest> findByCreator(String userId) {
        if (userId == null || userId.isBlank()) {
            return List.of();
        }

        List<WorkRequest> result = new ArrayList<>();

        for (WorkRequest request : requestRepository.findAll()) {
            User createdBy = request.getCreatedBy();

            if (createdBy != null
                    && createdBy.getId() != null
                    && createdBy.getId().equals(userId)) {
                result.add(request);
            }
        }

        return result;
    }
    public List<WorkRequest> findByTechnician(String technicianId) {
        if (technicianId == null || technicianId.isBlank()) {
            return List.of();
        }
        List <WorkRequest> result = new ArrayList<>();
        for (WorkRequest request : requestRepository.findAll()) {
            User createdBy = request.getCreatedBy();
            if (createdBy != null 
                    && createdBy.getId() != null
                    && createdBy.getId().equals(technicianId)) {
                        result.add(request);
                    }
        }
        return result;
    }
    

    public WorkRequest createRequest(
            String title,
            String description,
            RequestCategory category,
            RequestPriority priority,
            User createdBy
    ) {
        if (createdBy == null
                || !Boolean.TRUE.equals(createdBy.isActive())
                || createdBy.getDepartment() == null) {
            return null;
        }

        if (title == null
                || title.isBlank()
                || description == null
                || description.isBlank()
                || category == null
                || priority == null) {
            return null;
        }

        if (requestRepository.existsActiveRequestByTitleAndCreatedBy(
                title.trim(),
                createdBy.getId())) {
            return null;
        }

        WorkRequest request = new WorkRequest(
            requestNumberGenerator.nextId(),
            title.trim(),
            description.trim(),
            category,
            priority,
            createdBy,
            createdBy.getDepartment()
        );

        return requestRepository.save(request);
    }

    public WorkRequest findById(String requestId) {
        if (requestId == null || requestId.isBlank()) {
            return null;
        }

        return requestRepository.findById(requestId).orElse(null);
    }

    public List<WorkRequest> findAll() {
        return requestRepository.findAll();
    }

    public WorkRequest assignRequest(
            String requestNumber,
            User technician,
            User assignedBy
    ) {
        if (requestNumber == null
                || requestNumber.isBlank()
                || technician == null
                || assignedBy == null) {
            return null;
        }

        Optional<WorkRequest> requestOptional =
            requestRepository.findByRequestNumber(requestNumber);

        if (requestOptional.isEmpty()) {
            return null;
        }

        WorkRequest request = requestOptional.get();

        if (request.isTerminal()
                || !Boolean.TRUE.equals(assignedBy.isActive())
                || (!assignedBy.isAdmin()
                    && !Boolean.TRUE.equals(assignedBy.isManager()))) {
            return null;
        }

        if (!Boolean.TRUE.equals(technician.isActive())
                || !Boolean.TRUE.equals(technician.isTechnician())
                || technician.getDepartment() == null
                || !request.belongsToDepartment(
                    technician.getDepartment().getId())) {
            return null;
        }

        request.assignTo(technician, LocalDateTime.now());
        return requestRepository.save(request);
    }

    public WorkRequest changeStatus(
            String requestNumber,
            RequestStatus newStatus,
            User changedBy
    ) {
        if (requestNumber == null
                || requestNumber.isBlank()
                || newStatus == null
                || changedBy == null) {
            return null;
        }

        Optional<WorkRequest> requestOptional =
            requestRepository.findByRequestNumber(requestNumber);

        if (requestOptional.isEmpty()) {
            return null;
        }

        WorkRequest request = requestOptional.get();

        if (request.isTerminal()
                || !Boolean.TRUE.equals(changedBy.isActive())) {
            return null;
        }

        boolean authorized =
            changedBy.isAdmin()
                || Boolean.TRUE.equals(changedBy.isManager())
                || request.isAssignedTo(changedBy.getId());

        if (!authorized
                || !canTransition(request.getStatus(), newStatus)) {
            return null;
        }

        request.transitionTo(
            newStatus,
            changedBy,
            LocalDateTime.now()
        );

        return requestRepository.save(request);
    }

    private boolean canTransition(
            RequestStatus currentStatus,
            RequestStatus newStatus
    ) {
        if (currentStatus == null || newStatus == null) {
            return false;
        }

        return switch (currentStatus) {
            case ASSIGNED ->
                newStatus == RequestStatus.IN_PROGRESS
                    || newStatus == RequestStatus.CANCELLED;

            case IN_PROGRESS ->
                newStatus == RequestStatus.WAITING_FOR_PART
                    || newStatus == RequestStatus.COMPLETED
                    || newStatus == RequestStatus.CANCELLED;

            case WAITING_FOR_PART ->
                newStatus == RequestStatus.IN_PROGRESS
                    || newStatus == RequestStatus.CANCELLED;

            default -> false;
        };
    }
    
}
