package com.synthilearn.workareaservice.infra.rest;

import com.synthilearn.commonstarter.GenericResponse;
import com.synthilearn.securestarter.AccessToken;
import com.synthilearn.workareaservice.app.services.WorkareaService;
import com.synthilearn.workareaservice.domain.Workarea;
import com.synthilearn.workareaservice.domain.WorkareaType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workarea-service/v1/workarea")
public class WorkareaController {

    private final WorkareaService workareaService;

    @PostMapping("/{type}")
    public Mono<GenericResponse<Workarea>> initWorkarea(@PathVariable WorkareaType type,
                                                        AccessToken accessToken) {
        return workareaService.initWorkarea(type, accessToken.getId())
                .map(GenericResponse::ok);
    }

    @GetMapping("/all")
    public Mono<GenericResponse<List<Workarea>>> findAll(AccessToken accessToken) {
        return workareaService.findAll(accessToken.getId())
                .collectList()
                .map(GenericResponse::ok);
    }

    @GetMapping("/{id}")
    public Mono<GenericResponse<Workarea>> findOne(@PathVariable UUID id) {
        return workareaService.findOne(id)
                .map(GenericResponse::ok);
    }
}
