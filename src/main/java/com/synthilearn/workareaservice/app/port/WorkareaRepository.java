package com.synthilearn.workareaservice.app.port;

import com.synthilearn.workareaservice.domain.Workarea;
import com.synthilearn.workareaservice.domain.WorkareaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface WorkareaRepository {

    Mono<Workarea> findByWorkspaceAndType(WorkareaType type, UUID workspaceId);

    Mono<Workarea> saveWorkarea(WorkareaType type, UUID workspaceId);

    Flux<Workarea> findAll(UUID workspaceId);

    Mono<Workarea> findOne(UUID workareaId);
}
