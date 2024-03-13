package com.synthilearn.workareaservice.app.port;

import com.synthilearn.workareaservice.domain.AbstractWidget;
import com.synthilearn.workareaservice.domain.WidgetType;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface WidgetRepository {

    Mono<Void> addWidgets(Map<WidgetType, Mono<AbstractWidget>> widgets);
}
