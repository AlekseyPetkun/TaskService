package com.skypro.courswork_2.exceptions;

/**
 * Исключение при отсутствии задачи по Id.
 */

public class TaskNotFoundException extends Exception{
    private final int id;

    public TaskNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Задача с Id " + id + " не найдена!";
    }
}
