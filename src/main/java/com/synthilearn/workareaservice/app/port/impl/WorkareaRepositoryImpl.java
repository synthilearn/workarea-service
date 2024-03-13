package com.synthilearn.workareaservice.app.port.impl;

import com.synthilearn.workareaservice.app.port.WorkareaRepository;
import com.synthilearn.workareaservice.domain.Workarea;
import com.synthilearn.workareaservice.domain.WorkareaStatus;
import com.synthilearn.workareaservice.domain.WorkareaType;
import com.synthilearn.workareaservice.infra.jpa.entity.WorkareaEntity;
import com.synthilearn.workareaservice.infra.jpa.mapper.WorkareaEntityMapper;
import com.synthilearn.workareaservice.infra.jpa.repository.WidgetJpaRepository;
import com.synthilearn.workareaservice.infra.jpa.repository.WorkareaJpaRepository;
import com.synthilearn.workareaservice.infra.rest.exception.WorkareaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkareaRepositoryImpl implements WorkareaRepository {

    private final WorkareaJpaRepository workareaJpaRepository;
    private final WidgetJpaRepository widgetJpaRepository;
    private final WorkareaEntityMapper workareaEntityMapper;

    @Override
    public Mono<Workarea> findByWorkspaceAndType(WorkareaType type, UUID workspaceId) {
        return workareaJpaRepository.findByTypeAndWorkspaceId(type, workspaceId)
                .map(workareaEntityMapper::map);
    }

    @Override
    @Transactional
    public Mono<Workarea> saveWorkarea(WorkareaType type, UUID workspaceId) {
        return workareaJpaRepository.save(WorkareaEntity.builder()
                        .id(UUID.randomUUID())
                        .type(type)
                        .workspaceId(workspaceId)
                        .name(type.getTitle())
                        .status(WorkareaStatus.ACTIVE)
                        .newRecord(true)
                        .build())
                .map(workareaEntityMapper::map);
    }

    @Override
    public Flux<Workarea> findAll(UUID workspaceId) {
        return workareaJpaRepository.findByWorkspaceId(workspaceId)
                .map(workareaEntityMapper::map);
    }

    @Override
    public Mono<Workarea> findOne(UUID workareaId) {
        return workareaJpaRepository.findById(workareaId)
                .switchIfEmpty(Mono.error(WorkareaException.notFound(workareaId)))
                .flatMap(workarea -> widgetJpaRepository.findByWorkareaId(workareaId)
                        .collectList()
                        .map(widgets -> {
                            workarea.setWidgets(widgets);
                            return workarea;
                        }))
                .map(workareaEntityMapper::map);
    }
}
