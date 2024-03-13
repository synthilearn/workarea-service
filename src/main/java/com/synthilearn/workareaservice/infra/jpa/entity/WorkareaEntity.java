package com.synthilearn.workareaservice.infra.jpa.entity;

import com.synthilearn.workareaservice.domain.WorkareaStatus;
import com.synthilearn.workareaservice.domain.WorkareaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;

@Table("workarea")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkareaEntity implements Persistable<UUID> {

    @Id
    private UUID id;
    @Column("workspace_id")
    private UUID workspaceId;
    private String name;
    private WorkareaStatus status;
    private WorkareaType type;
    @Transient
    private List<WidgetEntity> widgets;

    @Transient
    private boolean newRecord;

    @Override
    public boolean isNew() {
        return this.newRecord;
    }
}
