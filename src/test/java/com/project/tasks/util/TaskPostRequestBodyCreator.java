package com.project.tasks.util;

import com.project.tasks.requests.TaskPostRequestBody;

public class TaskPostRequestBodyCreator {

    public static TaskPostRequestBody createTaskPostRequestBody() {
        return TaskPostRequestBody.builder()
                .description(TaskCreator.createTaskToBeSaved().getDescription())
                .build();
    }
}
