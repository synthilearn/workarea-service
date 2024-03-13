package com.synthilearn.workareaservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Workarea {

    private UUID id;
    private UUID workspaceId;
    private String name;
    private WorkareaStatus status;
    private WorkareaType type;
    private List<Widget> widgets;
}