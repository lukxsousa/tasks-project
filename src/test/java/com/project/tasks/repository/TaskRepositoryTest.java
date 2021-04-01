package com.project.tasks.repository;

import com.project.tasks.model.Task;
import com.project.tasks.util.TaskCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Task Repository")
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    @DisplayName("Save persists task when Successful")
    void save_PersistTask_WhenSuccessful() {

        Task taskToBeSaved = TaskCreator.createTaskToBeSaved();
        Task taskSaved = this.taskRepository.save(taskToBeSaved);
        Assertions.assertThat(taskSaved).isNotNull();
        Assertions.assertThat(taskSaved.getId()).isNotNull();
        Assertions.assertThat(taskSaved.getDescription()).isEqualTo(taskToBeSaved.getDescription());
    }

    @Test
    @DisplayName("Save updates task when Successful")
    void save_UpdatesTask_WhenSuccessful() {

        Task taskToBeSaved = TaskCreator.createTaskToBeSaved();
        Task taskSaved = this.taskRepository.save(taskToBeSaved);
        taskSaved.setDescription("Estudar");
        Task taskUpdated = this.taskRepository.save(taskSaved);
        Assertions.assertThat(taskUpdated).isNotNull();
        Assertions.assertThat(taskUpdated.getId()).isNotNull();
        Assertions.assertThat(taskUpdated.getDescription()).isEqualTo(taskToBeSaved.getDescription());
    }

    @Test
    @DisplayName("Delete removes task when Successful")
    void delete_RemovesTask_WhenSuccessful() {

        Task taskToBeSaved = TaskCreator.createTaskToBeSaved();
        Task taskSaved = this.taskRepository.save(taskToBeSaved);
        this.taskRepository.delete(taskSaved);
        Optional<Task> taskOptional = this.taskRepository.findById(taskSaved.getId());
        Assertions.assertThat(taskOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by description returns list of tasks when Successful")
    void findByDescription_ReturnsListOfTasks_WhenSuccessful() {

        Task taskToBeSaved = TaskCreator.createTaskToBeSaved();
        Task taskSaved = this.taskRepository.save(taskToBeSaved);
        String description = taskSaved.getDescription();
        List<Task> tasks = this.taskRepository.findByDescription(description);
        Assertions.assertThat(description)
                .isNotEmpty();
    }

    @Test
    @DisplayName("Find by description returns empty list when no task is found")
    void findByDescription_ReturnsEmptyList_TaskIsNotFound() {

        List<Task> tasks = this.taskRepository.findByDescription("XXX");
        Assertions.assertThat(tasks).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when description is empty")
    void save_ThrowsConstraintViolationException_WhenDescriptionIsEmpty() {

        Task task =  new Task();
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.taskRepository.save(task))
                .withMessageContaining("The task description cannot be empty");
    }
}