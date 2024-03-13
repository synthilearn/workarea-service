package com.synthilearn.workareaservice.infra.rest.exception;

import com.synthilearn.workareaservice.domain.WorkareaType;
import com.synthilearn.workareaservice.infra.rest.exception.parent.GenericException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class WorkareaException extends GenericException {
    public WorkareaException(String message, HttpStatus status, Integer code) {
        super(message, status, code);
    }

    public static WorkareaException alreadyExists(WorkareaType type, UUID workspaceId) {
        return new WorkareaException(String.format("Workarea with type: %s and workspace: %s already exists", type, workspaceId), HttpStatus.BAD_REQUEST, 1000);
    }

    public static WorkareaException notFound(UUID workareaId) {
        return new WorkareaException(String.format("Workarea with id: %s not found", workareaId), HttpStatus.NOT_FOUND, 1000);
    }
}
