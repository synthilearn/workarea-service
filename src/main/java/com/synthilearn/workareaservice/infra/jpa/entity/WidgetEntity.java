package com.synthilearn.workareaservice.infra.jpa.entity;

import com.synthilearn.workareaservice.domain.WidgetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("widget")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WidgetEntity implements Persistable<UUID> {

    @Id
    private UUID id;
    @Column("workarea_id")
    private UUID workareaId;
    private String name;
    private WidgetType type;

    @Transient
    private boolean newRecord;

    @Override
    public boolean isNew() {
        return this.newRecord;
    }
}
