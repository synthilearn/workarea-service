package com.synthilearn.workareaservice.infra.rest.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateDictionaryDto(@NotNull UUID workareaId) {
}
