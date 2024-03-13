package com.synthilearn.workareaservice.infra.jpa.repository;

import com.synthilearn.workareaservice.infra.jpa.entity.WidgetEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface WidgetJpaRepository extends ReactiveCrudRepository<WidgetEntity, UUID> {

    Flux<WidgetEntity> findByWorkareaId(UUID workareaId);
}
