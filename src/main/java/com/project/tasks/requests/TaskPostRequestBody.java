package com.project.tasks.requests;

import com.project.tasks.config.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskPostRequestBody {

    @NotEmpty(message = "The task description cannot be empty!")
    private String description;

    private TaskStatus status;
}
