package com.project.tasks.requests;

import com.project.tasks.config.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskPutRequestBody {

    private Long id;

    private String description;

    private TaskStatus status;
}
