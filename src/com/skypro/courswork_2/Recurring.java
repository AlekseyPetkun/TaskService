package com.skypro.courswork_2;

/**
 * Повторение задач.
 */

import java.time.LocalDateTime;


public interface Recurring {

    LocalDateTime getTaskNextTime(LocalDateTime localDateTime);

}
