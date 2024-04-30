package com.synthilearn.workareaservice.domain;

import lombok.Getter;

@Getter
public enum WorkareaType {
    LEARN_LANGUAGE("Изучение английского");

    private final String title;

    WorkareaType(String title) {
        this.title = title;
    }
}
