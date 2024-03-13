package com.synthilearn.workareaservice.app.config;

import com.synthilearn.workareaservice.domain.AbstractWidget;
import com.synthilearn.workareaservice.domain.WidgetType;
import com.synthilearn.workareaservice.domain.WorkareaType;
import com.synthilearn.workareaservice.infra.adapter.client.DictionaryClient;
import com.synthilearn.workareaservice.infra.rest.dto.CreateDictionaryDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Configuration
public class WorkareaProcessorConfig {

    @Bean
    public Map<WorkareaType, Function<UUID, Map<WidgetType, Mono<AbstractWidget>>>> widgetsSupplier(DictionaryClient dictionaryClient) {
        return new HashMap<>() {{
            put(WorkareaType.LEARN_LANGUAGE, id -> new HashMap<>() {{
                put(WidgetType.DICTIONARY, dictionaryClient.createDictionary(new CreateDictionaryDto(id)));
            }});
        }};
    }
}
