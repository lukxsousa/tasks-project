package com.project.tasks.util;

import com.project.tasks.requests.TaskPutRequestBody;

public class TaskPutRequestBodyCreator {

    public static TaskPutRequestBody createTaskPutRequestBody() {
        return TaskPutRequestBody.builder()
                .id(TaskCreator.createValidUpdateTask().getId())
                .description(TaskCreator.createValidUpdateTask().getDescription())
                .build();
    }
}
