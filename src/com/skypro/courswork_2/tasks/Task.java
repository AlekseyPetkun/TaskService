package com.skypro.courswork_2.tasks;
/**
 * Общий класс для задач.
 */

import com.skypro.courswork_2.Recurring;
import com.skypro.courswork_2.TaskType;
import com.skypro.courswork_2.exceptions.IncorrectArgumentException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public abstract class Task implements Recurring{

    private String taskTitle;
    private String taskDescription;
    private static int idCounter = 1;
    private int id;
    private final TaskType taskType;

    private LocalDateTime localDateTime;

    public Task(String taskTitle,
                String taskDescription,
                TaskType taskType,
                LocalDateTime localDateTime) throws IncorrectArgumentException {
        setTaskTitle(taskTitle);
        setTaskDescription(taskDescription);
        setLocalDateTime(localDateTime);
        setId();
        if (taskType == null) {
            throw new IncorrectArgumentException("тип задачи!");
        } else {
            this.taskType = taskType;
        }
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) throws IncorrectArgumentException {
        if (taskTitle == null || taskTitle.isEmpty() || taskTitle.isBlank()) {
            throw new IncorrectArgumentException("название задачи!");
        } else {
            this.taskTitle = taskTitle;
        }
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) throws IncorrectArgumentException {
        if (taskDescription == null || taskDescription.isEmpty() || taskDescription.isBlank()) {
            throw new IncorrectArgumentException("описание задачи!");
        } else {
            this.taskDescription = taskDescription;
        }
    }

    public int getId() {
        return this.id;
    }

    public void setId() {
        this.id = idCounter++;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) throws IncorrectArgumentException {
        if (localDateTime == null) {
            throw new IncorrectArgumentException("дата и время задачи!");
        } else {
            this.localDateTime = localDateTime;
        }
    }

    public TaskType getTaskType() {
        return taskType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(taskTitle, task.taskTitle) && Objects.equals(taskDescription,
                task.taskDescription) && taskType == task.taskType && Objects.equals(localDateTime, task.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskTitle, taskDescription, id, taskType, localDateTime);
    }

    @Override
    public String toString() {
        if (taskDescription == null) {
            return "id - " + getId() +
                    ". Заголовок задачи: " + getTaskTitle() +
                    ". Дата задачи: " + getLocalDateTime();
        } else {
            return "id - " + getId() +
                    ". Заголовок задачи: " + getTaskTitle() +
                    ". Описание задачи: " + getTaskDescription() +
                    ". Дата задачи: " + getLocalDateTime();
        }
    }
}
