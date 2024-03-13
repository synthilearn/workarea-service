package com.synthilearn.workareaservice.infra.jpa.repository;

import com.synthilearn.workareaservice.domain.WorkareaType;
import com.synthilearn.workareaservice.infra.jpa.entity.WorkareaEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface WorkareaJpaRepository extends ReactiveCrudRepository<WorkareaEntity, UUID> {

    Mono<WorkareaEntity> findByTypeAndWorkspaceId(WorkareaType type, UUID workspaceId);
    Flux<WorkareaEntity> findByWorkspaceId(UUID workspaceId);
}
