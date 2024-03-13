package com.synthilearn.workareaservice.infra.jpa.mapper;

import com.synthilearn.workareaservice.domain.Widget;
import com.synthilearn.workareaservice.domain.Workarea;
import com.synthilearn.workareaservice.infra.jpa.entity.WidgetEntity;
import com.synthilearn.workareaservice.infra.jpa.entity.WorkareaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WorkareaEntityMapper {

    Workarea map(WorkareaEntity workarea);
    Widget map(WidgetEntity widgetEntity);
}
