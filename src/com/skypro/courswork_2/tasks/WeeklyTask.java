package com.skypro.courswork_2.tasks;

import com.skypro.courswork_2.TaskType;
import com.skypro.courswork_2.exceptions.IncorrectArgumentException;

import java.time.LocalDateTime;

/**
 * Еженедельная задача.
 */
public class WeeklyTask extends Task {

    public WeeklyTask(String taskTitle,
                      String taskDescription,
                      TaskType taskType,
                      LocalDateTime localDateTime) throws IncorrectArgumentException {

        super(taskTitle, taskDescription, taskType, localDateTime);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime localDateTime) {

        return localDateTime.plusWeeks(1);
    }
}
