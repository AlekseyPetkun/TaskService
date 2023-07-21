package com.skypro.courswork_2;

import java.time.LocalDateTime;

/**
 * Повторение задач.
 */
public interface Recurring {

    LocalDateTime getTaskNextTime(LocalDateTime localDateTime);
}
