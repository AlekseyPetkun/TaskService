package com.skypro.courswork_2;

import com.skypro.courswork_2.exceptions.TaskNotFoundException;
import com.skypro.courswork_2.tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервис по управлению задачами.
 */
public class TaskService {

    private final Map<Integer, Task> taskMap = new HashMap<>();

    public void addTask(Task task) {

        this.taskMap.put(task.getId(), task);
    }
    public void removeTask(int id) throws TaskNotFoundException {

        if (taskMap.containsKey(id)) {
            this.taskMap.remove(id);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    public Collection<Task> getAllByDate(LocalDate date) {

        Collection<Task> tasksByDay = new ArrayList<>();
        Collection<Task> allTasks = taskMap.values();

        for (Task allTask : allTasks) {
            LocalDateTime currentDateTime = allTask.getLocalDateTime();
            if (currentDateTime.toLocalDate().equals(date)) {
                tasksByDay.add(allTask);
                break;
            }

            LocalDateTime taskNextTime = currentDateTime;
            do {
                taskNextTime = allTask.getTaskNextTime(taskNextTime);

                if (taskNextTime == null) {
                    break;
                }

                if (taskNextTime.toLocalDate().equals(date)) {
                    tasksByDay.add(allTask);
                    break;
                }
            }
            while (taskNextTime.toLocalDate().isBefore(date));
        }
        return tasksByDay;
    }
}
