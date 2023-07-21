package com.skypro.courswork_2;

/**
 * Тип задачи.
 */
public enum TaskType {

    HOME_TASK("Личная задача"),
    WORK_TASK("Рабочая задача");

    private final String translation;

    TaskType(String translation) {
        this.translation = translation;
    }

    public String getTranslation() {
        return translation;
    }

    @Override
    public String toString() {
        return "Тип задачи: " +
                getTranslation();
    }
}
