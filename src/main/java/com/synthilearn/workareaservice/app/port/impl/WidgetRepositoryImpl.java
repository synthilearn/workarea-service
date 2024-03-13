package com.synthilearn.workareaservice.app.port.impl;

import com.synthilearn.workareaservice.app.port.WidgetRepository;
import com.synthilearn.workareaservice.domain.AbstractWidget;
import com.synthilearn.workareaservice.domain.WidgetType;
import com.synthilearn.workareaservice.infra.jpa.entity.WidgetEntity;
import com.synthilearn.workareaservice.infra.jpa.repository.WidgetJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WidgetRepositoryImpl implements WidgetRepository {

    private final WidgetJpaRepository widgetJpaRepository;

    @Override
    @Transactional
    public Mono<Void> addWidgets(Map<WidgetType, Mono<AbstractWidget>> widgets) {
        return Flux.fromIterable(widgets.entrySet())
                .flatMap(entry -> entry.getValue()
                        .map(abstractWidget -> WidgetEntity.builder()
                                .id(abstractWidget.getId())
                                .newRecord(true)
                                .workareaId(abstractWidget.getWorkareaId())
                                .name(entry.getKey().getTitle())
                                .type(entry.getKey())
                                .build()
                        )
                        .flatMap(widgetJpaRepository::save)
                )
                .then();
    }
}
