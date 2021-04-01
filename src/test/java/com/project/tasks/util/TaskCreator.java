package com.project.tasks.util;

import com.project.tasks.model.Task;

public class TaskCreator {

    public static Task createTaskToBeSaved() {
        return Task.builder()
                .description("Lavar a louça")
                .build();
    }

    public static Task createValidTask() {
        return Task.builder()
                .description("Arrumar a casa")
                .id(1L)
                .build();
    }

    public static Task createValidUpdateTask() {
        return Task.builder()
                .description("Assistir série")
                .id(1L)
                .build();
    }
}
