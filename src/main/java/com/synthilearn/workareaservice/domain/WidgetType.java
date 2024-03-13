package com.synthilearn.workareaservice.domain;

import lombok.Getter;

@Getter
public enum WidgetType {
    LIBRARY("Библиотека"), DICTIONARY("Словарь"), GAME("Игры"), STATISTIC("Статистика");

    private final String title;

    WidgetType(String title) {
        this.title = title;
    }
}
