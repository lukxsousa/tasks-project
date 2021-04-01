package com.project.tasks.service;

import com.project.tasks.exception.BadRequestException;
import com.project.tasks.mapper.TaskMapper;
import com.project.tasks.model.Task;
import com.project.tasks.repository.TaskRepository;
import com.project.tasks.requests.TaskPostRequestBody;
import com.project.tasks.requests.TaskPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Page<Task> listAll(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public List<Task> listAllNonPageable() {
        return taskRepository.findAll();
    }

    public List<Task> findByDescription(String description) {
        return taskRepository.findByDescription(description);
    }

    public Task findByIdOrThrowBadRequestException(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Task not found!"));
    }

    @Transactional
    public Task save(TaskPostRequestBody taskPostRequestBody) {
        return taskRepository.save(TaskMapper.INSTANCE.toTask(taskPostRequestBody));
    }

    public void delete(long id) {
        taskRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(TaskPutRequestBody taskPutRequestBody) {
        Task savedTask = findByIdOrThrowBadRequestException(taskPutRequestBody.getId());
        Task task = TaskMapper.INSTANCE.toTask(taskPutRequestBody);
        task.setId(savedTask.getId());
        taskRepository.save(task);
    }

}
