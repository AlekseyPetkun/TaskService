package com.skypro.courswork_2;


/**
 * Ежедневник.
 */

import com.skypro.courswork_2.exceptions.IncorrectArgumentException;
import com.skypro.courswork_2.exceptions.TaskNotFoundException;
import com.skypro.courswork_2.tasks.*;

import java.time.*;
import java.util.*;

public class Main {
    private static final TaskService taskService = new TaskService();

    public static void main(String[] args) throws IncorrectArgumentException {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            printAllTasksByDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) throws IncorrectArgumentException {
        System.out.println("Введите название задачи: ");
        String taskTitle = scanner.next();

        System.out.println("Введите описание задачи: ");
        String taskDescription = scanner.next();

        System.out.println("Выберете тип задачи: \n" +
                "1. Личная задача, \n" +
                "2. Рабочая задача");
        TaskType taskType = null;
        int numberType = scanner.nextInt();
        switch (numberType) {
            case 1:
                taskType = TaskType.HOME_TASK;
                break;
            case 2:
                taskType = TaskType.WORK_TASK;
                break;
            default:
                System.out.println("Такого пункта нет! Выберете тип задачи!");
        }

        System.out.print("Дата и время выполнения задачи.");

        System.out.print("Укажите день в формате dd: ");
        int day = scanner.nextInt();
        System.out.print("Укажите месяц в формате mm: ");
        int month = scanner.nextInt();
        System.out.print("Укажите год в формате yyyy: ");
        int year = scanner.nextInt();
        System.out.print("Укажите часы в формате hh: ");
        int hour = scanner.nextInt();
        System.out.print("Укажите минуты в формате mm: ");
        int minute = scanner.nextInt();

        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute);
        if (localDateTime == null) {
            System.out.println("Введите дату и время выполнения задачи!");
        }

        System.out.println("Введите повторяемость задачи: \n" +
                "1. однократно;\n" +
                "2. ежедневно;\n" +
                "3. еженедельно;\n" +
                "4. ежемесячно;\n" +
                "5. ежегодно.");

        Task task = null;

        if (scanner.hasNextInt()) {
            int numberRec = scanner.nextInt();

            switch (numberRec) {
                case 1:
                    task = new OnceTask(taskTitle, taskDescription, taskType, localDateTime);
                    break;
                case 2:
                    task = new DailyTask(taskTitle, taskDescription, taskType, localDateTime);
                    break;
                case 3:
                    task = new WeeklyTask(taskTitle, taskDescription, taskType, localDateTime);
                    break;
                case 4:
                    task = new MonthlyTask(taskTitle, taskDescription, taskType, localDateTime);
                    break;
                case 5:
                    task = new YearlyTask(taskTitle, taskDescription, taskType, localDateTime);
                    break;
                default:
                    System.out.println("Такого пункта нет! Выберете повторяемость задачи!");
            }
        }
        taskService.addTask(task);
        System.out.println("Задача добавлена!");
    }

    private static void printMenu() {
        System.out.println(
                "1. Добавить задачу\n" +
                        "2. Удалить задачу\n" +
                        "3. Получить задачу на указанный день\n" +
                        "0. Выход"
        );
    }

    private static void removeTask(Scanner scanner) {
        System.out.println("Введите номер Id задачи для удаления!");
        int id = scanner.nextInt();

        try {
            taskService.removeTask(id);
        } catch (TaskNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public static void printAllTasksByDay(Scanner scanner) {
        System.out.print("Введите дату.");

        System.out.print("Укажите день в формате dd: ");
        int day = scanner.nextInt();
        System.out.print("Укажите месяц в формате mm: ");
        int month = scanner.nextInt();
        System.out.print("Укажите год в формате yyyy: ");
        int year = scanner.nextInt();

        LocalDate localDate = LocalDate.of(year, month, day);

        Collection<Task> tasks = taskService.getAllByDate(localDate);
        System.out.println(tasks);

        if (localDate == null) {
            System.out.println("Введите дату!");
        }
    }
}
