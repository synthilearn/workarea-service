package com.synthilearn.workareaservice.app.services;

import com.synthilearn.workareaservice.domain.Workarea;
import com.synthilearn.workareaservice.domain.WorkareaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface WorkareaService {

    Mono<Workarea> initWorkarea(WorkareaType workareaType, UUID workspaceId);
    Flux<Workarea> findAll(UUID workspaceId);
    Mono<Workarea> findOne(UUID workareaId);
}
