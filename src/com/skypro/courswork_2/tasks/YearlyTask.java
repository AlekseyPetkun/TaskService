package com.skypro.courswork_2.tasks;
/**
 * Ежегодная задача.
 */

import com.skypro.courswork_2.TaskType;
import com.skypro.courswork_2.exceptions.IncorrectArgumentException;


import java.time.LocalDateTime;

public class YearlyTask extends Task {
    public YearlyTask(String taskTitle,
                      String taskDescription,
                      TaskType taskType,
                      LocalDateTime localDateTime) throws IncorrectArgumentException {
        super(taskTitle, taskDescription, taskType, localDateTime);
    }

    @Override
    public LocalDateTime getTaskNextTime(LocalDateTime localDateTime) {
        return localDateTime.plusYears(1);
    }
}
