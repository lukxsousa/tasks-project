package com.project.tasks.mapper;

import com.project.tasks.model.Task;
import com.project.tasks.requests.TaskPostRequestBody;
import com.project.tasks.requests.TaskPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {

    public static final TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    public abstract Task toTask(TaskPostRequestBody taskPostRequestBody);

    public abstract Task toTask(TaskPutRequestBody taskPutRequestBody);
}
