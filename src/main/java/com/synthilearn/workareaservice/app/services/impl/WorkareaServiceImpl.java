package com.synthilearn.workareaservice.app.services.impl;

import com.synthilearn.workareaservice.app.port.WidgetRepository;
import com.synthilearn.workareaservice.app.port.WorkareaRepository;
import com.synthilearn.workareaservice.app.services.WorkareaService;
import com.synthilearn.workareaservice.domain.*;
import com.synthilearn.workareaservice.infra.rest.exception.WorkareaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkareaServiceImpl implements WorkareaService {

    private final WorkareaRepository workareaRepository;
    private final WidgetRepository widgetRepository;
    private final Map<WorkareaType, Function<UUID, Map<WidgetType, Mono<AbstractWidget>>>> widgetsSupplier;

    @Override
    @Transactional
    public Mono<Workarea> initWorkarea(WorkareaType workareaType, UUID workspaceId) {
        return workareaRepository.findByWorkspaceAndType(workareaType, workspaceId)
                .singleOptional()
                .flatMap(workareaOpt -> {
                    if (workareaOpt.isPresent()) {
                        return Mono.error(WorkareaException.alreadyExists(workareaType, workspaceId));
                    }
                    return Mono.just(new Workarea());
                })
                .flatMap(workarea -> workareaRepository.saveWorkarea(workareaType, workspaceId))
                .flatMap(workarea -> {
                    Map<WidgetType, Mono<AbstractWidget>> widgetsIds = widgetsSupplier.get(workareaType).apply(workarea.getId());
                    return widgetRepository.addWidgets(widgetsIds)
                            .thenReturn(workarea);
                });
    }

    @Override
    public Flux<Workarea> findAll(UUID workspaceId) {
        return workareaRepository.findAll(workspaceId);
    }

    @Override
    public Mono<Workarea> findOne(UUID workareaId) {
        return workareaRepository.findOne(workareaId);
    }
}
